package json

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json._

import domain.{Buy, Fee, FixedFee, PercentFee, Sell, Side, Ticker, Transaction, TradingPair}

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val tickerFormat = jsonFormat1(Ticker)
  implicit val tradingPair  = jsonFormat2(TradingPair)

  implicit object SideJsonFormat extends RootJsonFormat[Side] {
    def write(side: Side): JsValue = side match {
      case Buy  => "buy".toJson
      case Sell => "sell".toJson
    }

    def read(value: JsValue): Side = value match {
      case JsString("buy")  => Buy
      case JsString("sell") => Sell
      case _                => deserializationError("side expected")
    }
  }

  implicit object FeeJsonFormat extends RootJsonFormat[Fee] {
    def write(fee: Fee): JsValue = JsObject(
      "type" -> new JsString(fee match {
        case _: PercentFee => "percent"
        case _: FixedFee   => "fixed"
      }),
      "value"  -> JsNumber(fee.value),
      "ticker" -> fee.ticker.toJson
    )

    def read(value: JsValue): Fee =
      value.asJsObject.getFields("type", "value", "ticker") match {
        case Seq(JsString(feeType), JsNumber(value), ticker: JsObject) =>
          feeType match {
            case "percent" => PercentFee(value.toDouble, ticker.convertTo[Ticker])
            case "fixed"   => FixedFee(value.toDouble, ticker.convertTo[Ticker])
            case _         => deserializationError("Fee expected")
          }
        case _ => deserializationError("Fee expected")
      }
  }

  implicit val transactionFormat = jsonFormat10(Transaction)
}
