package graphite

import shapeless.HList

sealed trait Query[P] {
  val path: P
}

case class SimpleQuery[P <: HList](path: P) extends Query[P]

case class AggregationQuery[P <: HList](path: P, aggregation: Iterable[String]) extends Query[P]

object Query {

  def apply[P <: HList](path: P): Query[P] = {
    SimpleQuery[P](path)
  }

}




