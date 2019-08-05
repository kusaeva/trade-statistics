package domain.dictionaries

import domain.{Currency, Ticker}

object Dictionary {

  val exhanges = Seq("Binance", "BitMEX")
  val currencies =
    Seq(Currency(Ticker("BTC"), "Bitcoin"), Currency(Ticker("USDT"), "Tether"))
}
