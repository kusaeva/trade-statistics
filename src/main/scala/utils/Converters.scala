package utils

import binance_stream_api.Trade

object Converters {
  def binanceTradeConverter(trade: Trade): domain.Trade = {
    domain.Trade(
      evType = trade.e,
      evTime = trade.E,
      symbol = trade.s,
      id = trade.t,
      price = trade.p,
      quantity = trade.q,
      buyerOrderId = trade.b,
      sellerOrderId = trade.a,
      tradeTime = trade.T,
      isMarketMaker = trade.m
    )
  }
}
