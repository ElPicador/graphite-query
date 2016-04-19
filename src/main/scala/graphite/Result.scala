package graphite

import shapeless.HList

import scala.annotation.implicitNotFound

object Result {

  implicit def mkResult0[PATH <: HList] = new ResultMaker[PATH, None.type] {
    override type RESULT = Result0[PATH]

    override def apply(statement: Statement[PATH, None.type], entries: Iterable[GraphiteEntry]): Result0[PATH] = Result0(statement, entries)
  }

  implicit def mkResult1[PATH <: HList, VALUES <: Product1[_]] = new ResultMaker[PATH, VALUES] {
    override type RESULT = Result1[PATH, VALUES]

    override def apply(statement: Statement[PATH, VALUES], entries: Iterable[GraphiteEntry]): Result1[PATH, VALUES] = Result1(statement, entries)
  }

  implicit def mkResult2[PATH <: HList, VALUES <: Product2[_, _]] = new ResultMaker[PATH, VALUES] {
    override type RESULT = Result2[PATH, VALUES]

    override def apply(statement: Statement[PATH, VALUES], entries: Iterable[GraphiteEntry]): Result2[PATH, VALUES] = Result2(statement, entries)
  }

  @implicitNotFound("Could not find a conversion from Statement[${PATH}, ${VALUES}] to a Result type (probably a bug)")
  sealed trait ResultMaker[PATH <: HList, VALUES <: Product] {
    type RESULT <: Result[PATH, VALUES]

    def apply(statement: Statement[PATH, VALUES], entries: Iterable[GraphiteEntry]): RESULT
  }

}

sealed trait Result[PATH <: HList, VALUES <: Product] {

  val statement: Statement[PATH, VALUES]
  val entries: Iterable[GraphiteEntry]

}

case class Result0[PATH <: HList](statement: Statement[PATH, None.type], entries: Iterable[GraphiteEntry]) extends Result[PATH, None.type]

case class Result1[PATH <: HList, VALUES <: Product1[_]](statement: Statement[PATH, VALUES], entries: Iterable[GraphiteEntry]) extends Result[PATH, VALUES]

case class Result2[PATH <: HList, VALUES <: Product2[_, _]](statement: Statement[PATH, VALUES], entries: Iterable[GraphiteEntry]) extends Result[PATH, VALUES]