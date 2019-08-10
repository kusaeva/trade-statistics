package binance_stream_api

final case class Trade(
  e: String,
  E: Long,
  s: String,
  t: Long,
  p: String,
  q: String,
  b: Long,
  a: Long,
  T: Long,
  m: Boolean,
  M: Boolean
)
