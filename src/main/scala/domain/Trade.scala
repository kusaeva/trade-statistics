package domain

case class Trade(
  evType: String,
  evTime: Long,
  symbol: String,
  id: Long,
  price: String,
  quantity: String,
  buyerOrderId: Long,
  sellerOrderId: Long,
  tradeTime: Long,
  isMarketMaker: Boolean
)
