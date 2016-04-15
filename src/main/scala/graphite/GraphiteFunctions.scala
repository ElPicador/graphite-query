package graphite

import shapeless.HList

object GraphiteFunctions {

  def averageSeries[P <: HList](path: P): Query[P] = {
    AggregationQuery(path, Seq("averageSeries"))
  }

}
