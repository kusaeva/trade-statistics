package json

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json._

import binance_stream_api.Trade

trait BinanceApiJsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val tradeFormat = jsonFormat11(Trade)
}
