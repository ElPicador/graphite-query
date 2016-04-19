package graphite

import graphite.Node.{*, ?}
import graphite.Result.ResultMaker
import graphite.Statement.StatementConstraint
import shapeless._

import scala.annotation.implicitNotFound

case class Statement[PATH <: HList, VALUES <: Product](
                                                        path: Query[PATH],
                                                        values: VALUES = None
                                                      )
                                                      (
                                                        implicit vc: StatementConstraint[PATH, VALUES]
                                                      ) {

  def apply(
             entries: Iterable[GraphiteEntry]
           )
           (
             implicit result: ResultMaker[PATH, VALUES]
           ): result.RESULT = {
    result(this, entries)
  }
}

object Statement {

  implicit def `?HNilTuple1`[VALUES <: Product1[_]] =
    new StatementConstraint[?.type :: HNil, VALUES] {}

  implicit def `?NodeTuple1`[PATH <: HList, VALUES <: Product1[_]](implicit i: StatementConstraint[PATH, None.type]) =
    new StatementConstraint[?.type :: PATH, VALUES] {}

  implicit def `?NodeTuple2`[PATH <: HList, VALUES <: Product2[_, _]](implicit i: StatementConstraint[PATH, Product1[_]]) =
    new StatementConstraint[?.type :: PATH, VALUES] {}

  implicit def HNilNothing =
    new StatementConstraint[HNil.type, None.type] {}

  implicit def stringHNilNothing =
    new StatementConstraint[String :: HNil, None.type] {}

  implicit def `*HNilNothing` =
    new StatementConstraint[*.type :: HNil, None.type] {}

  implicit def stringNodeAny[PATH <: HList, VALUES <: Product](implicit i: StatementConstraint[PATH, VALUES]) =
    new StatementConstraint[String :: PATH, VALUES] {}

  implicit def stringNodeNothing[PATH <: HList](implicit i: StatementConstraint[PATH, None.type]) =
    new StatementConstraint[String :: PATH, None.type] {}

  implicit def `*NodeAny`[PATH <: HList, VALUES <: Product](implicit i: StatementConstraint[PATH, VALUES]) =
    new StatementConstraint[*.type :: PATH, VALUES] {}

  implicit def `*NodeNothing`[PATH <: HList](implicit i: StatementConstraint[PATH, None.type]) =
    new StatementConstraint[*.type :: PATH, None.type] {}

  @implicitNotFound(msg = "Invalid Statement, the number of `?` should match the number of parameters")
  trait StatementConstraint[PATH, VALUES <: Product] extends Serializable


}