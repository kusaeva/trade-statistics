package domain

case class Ticker(name: String)

case class Currency(
  ticker: Ticker,
  name: String
)
