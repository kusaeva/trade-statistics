package domain

import utils.{TransactionId, ExchangeId}

sealed trait Side
final case object Buy  extends Side
final case object Sell extends Side

sealed trait Fee {
  def value: Double
  def ticker: Ticker
}
final case class PercentFee(value: Double, ticker: Ticker) extends Fee
final case class FixedFee(value: Double, ticker: Ticker)   extends Fee

final case class TradingPair(t1: Ticker, t2: Ticker)

case class Transaction(
  date: Long,
  id: TransactionId,
  side: Side,
  exchange: ExchangeId,
  ticker: Ticker,
  pair: TradingPair,
  price: Double,
  amount: Double,
  fee: Option[Fee] = None,
  notes: Option[String] = None
)
