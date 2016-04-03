package graphite

import shapeless.HList


case class Query[P <: HList](path: P, aggregation: Option[String] = None)