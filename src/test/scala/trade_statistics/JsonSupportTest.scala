package trade_statistics

import binance_stream_api.Trade
import json.BinanceApiJsonSupport
import org.scalatest.{Matchers, WordSpec}
import spray.json._

class JsonSupportTest extends WordSpec with Matchers with BinanceApiJsonSupport {
  "JsonSupport" should {
    "correctly read binance trade from json" in {
      val input: String =
        """{
          "e": "trade",
          "E": 123456789,
          "s": "BNBBTC",
          "t": 12345,
          "p": "0.001",
          "q": "100",
          "b": 88,
          "a": 50,
          "T": 123456785,
          "m": true,
          "M": true
        }"""
      val t = Trade(
        e = "trade",
        E = 123456789,
        s = "BNBBTC",
        t = 12345,
        p = "0.001",
        q = "100",
        b = 88,
        a = 50,
        T = 123456785,
        m = true,
        M = true
      )
      input.parseJson.convertTo[Trade] shouldEqual t
    }
  }

}
