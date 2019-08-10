package binance_stream_api

import akka.actor.ActorSystem
import akka.Done
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import akka.stream.scaladsl._
import akka.http.scaladsl.model._
import akka.http.scaladsl.model.ws._
import json.BinanceApiJsonSupport
import utils.Converters

import scala.concurrent.Future
import spray.json._

/**
  * Binance API integration
  * https://github.com/binance-exchange/binance-official-api-docs/blob/master/web-socket-streams.md
  */
object ApiClient extends App with BinanceApiJsonSupport {
  implicit val system       = ActorSystem()
  implicit val materializer = ActorMaterializer()
  import system.dispatcher

  val BASE_ENDPOINT = "wss://stream.binance.com:9443"

  val endpoint = s"$BASE_ENDPOINT/ws/btcusdt@trade"

  // print each incoming trade from stream
  val printSink: Sink[Message, Future[Done]] =
    Sink.foreach {
      case message: TextMessage.Strict => {
        val trade = message.text.parseJson.convertTo[Trade]
        println(Converters.binanceTradeConverter(trade))
      }
    }

  val flow: Flow[Message, Message, Future[Done]] =
    Flow.fromSinkAndSourceMat(printSink, Source.maybe)(Keep.left)

  val (upgradeResponse, closed) =
    Http().singleWebSocketRequest(WebSocketRequest(endpoint), flow)

  val connected = upgradeResponse.map { upgrade =>
    if (upgrade.response.status == StatusCodes.SwitchingProtocols) {
      Done
    } else {
      throw new RuntimeException(s"Connection failed: ${upgrade.response.status}")
    }
  }

  connected.onComplete(println)
  closed.foreach(_ => println("closed"))
}
