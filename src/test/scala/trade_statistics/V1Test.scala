package trade_statistics

import akka.http.scaladsl.model.{HttpEntity, HttpMethods, HttpRequest, MediaTypes, StatusCodes}
import akka.util.ByteString
import domain.{Buy, Ticker, TradingPair, Transaction}

class V1Test extends ApiAuthTest {

  val transactionRequest = ByteString(s"""
                                         |{
                                         |    "amount": 0.5,
                                         |    "date": 123456,
                                         |    "exchange": 1,
                                         |    "id": 1,
                                         |    "pair": {
                                         |        "t1": {
                                         |            "name": "BTC"
                                         |        },
                                         |        "t2": {
                                         |            "name": "USDT"
                                         |        }
                                         |    },
                                         |    "price": 10000.0,
                                         |    "side": "buy",
                                         |    "ticker": {
                                         |        "name": "BTC"
                                         |    }
                                         |}
        """.stripMargin)
  val createRequest = HttpRequest(
    HttpMethods.POST,
    uri = "/api/v1/create",
    entity = HttpEntity(MediaTypes.`application/json`, transactionRequest)
  )

  "POST /api/v1/create resp OK" in {
    createRequest ~> addCredentials(validCredentials) ~> routes ~> check {
      status shouldBe StatusCodes.OK
      responseAs[Transaction] shouldBe new Transaction(
        date = 123456,
        id = 1L,
        side = Buy,
        exchange = 1L,
        ticker = Ticker("BTC"),
        pair = TradingPair(Ticker("BTC"), Ticker("USDT")),
        price = 10000.0,
        amount = 0.5
      )
    }
  }
}
