package binance_stream_api

case class Event(evType: String, evTime: Long, symbol: String)
