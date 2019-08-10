package trade_statistics

import org.scalatest.{Matchers, WordSpec}
import utils.Converters

class ConvertersTest extends WordSpec with Matchers {
  "Converters" should {
    "convert Trade types correctly" in {
      val binanceTrade: binance_stream_api.Trade = binance_stream_api.Trade(
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
      val domainTrade: domain.Trade = domain.Trade(
        evType = "trade",
        evTime = 123456789,
        symbol = "BNBBTC",
        id = 12345,
        price = "0.001",
        quantity = "100",
        buyerOrderId = 88,
        sellerOrderId = 50,
        tradeTime = 123456785,
        isMarketMaker = true
      )
      Converters.binanceTradeConverter(binanceTrade) shouldEqual domainTrade
    }
  }
}
