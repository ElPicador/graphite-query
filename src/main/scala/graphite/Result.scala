package graphite

import shapeless.HList

import scala.annotation.implicitNotFound

object Result {

  implicit def mkResult0[P <: HList] = new ResultMaker[P, None.type] {
    override type R = Result0[P]

    override def apply(statement: Statement[P, None.type], entries: Iterable[GraphiteEntry]): Result0[P] = Result0(statement, entries)
  }

  implicit def mkResult1[P <: HList, V <: Product1[_]] = new ResultMaker[P, V] {
    override type R = Result1[P, V]

    override def apply(statement: Statement[P, V], entries: Iterable[GraphiteEntry]): Result1[P, V] = Result1(statement, entries)
  }

  implicit def mkResult2[P <: HList, V <: Product2[_, _]] = new ResultMaker[P, V] {
    override type R = Result2[P, V]

    override def apply(statement: Statement[P, V], entries: Iterable[GraphiteEntry]): Result2[P, V] = Result2(statement, entries)
  }

  @implicitNotFound("Could not find a conversion from Statement[${P}, ${V}] to a Result type (probably a bug)")
  sealed trait ResultMaker[P <: HList, V <: Product] {
    type R <: Result[P, V]

    def apply(statement: Statement[P, V], entries: Iterable[GraphiteEntry]): R
  }

}

sealed trait Result[P <: HList, V <: Product] {

  val statement: Statement[P, V]
  val entries: Iterable[GraphiteEntry]

}

case class Result0[P <: HList](statement: Statement[P, None.type], entries: Iterable[GraphiteEntry]) extends Result[P, None.type]

case class Result1[P <: HList, V <: Product1[_]](statement: Statement[P, V], entries: Iterable[GraphiteEntry]) extends Result[P, V]

case class Result2[P <: HList, V <: Product2[_, _]](statement: Statement[P, V], entries: Iterable[GraphiteEntry]) extends Result[P, V]