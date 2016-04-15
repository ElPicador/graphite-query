package graphite

/**
  * Output from graphite /render JSON API
{
  "target": "stats.gauges.latency.s1-10.transfer-time",
  "datapoints": [
    [
      80.0,
      1452854830
    ]
  ]
}
*/

case class GraphiteEntry(target: String, datapoints: Seq[Seq[Option[BigDecimal]]])