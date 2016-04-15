package graphite

import graphite.Node.{*, ?}
import graphite.Result.ResultMaker
import graphite.Statement.StatementConstraint
import shapeless._

import scala.annotation.implicitNotFound

case class Statement[P <: HList, V <: Product](
                                                path: Query[P],
                                                values: V = None
                                              )
                                              (
                                                implicit vc: StatementConstraint[P, V]
                                              ) {

  def apply(entries: Iterable[GraphiteEntry])(implicit result: ResultMaker[P, V]): result.R = {
    result(this, entries)
  }
}

object Statement {

  implicit def `?HNilTuple1`[V <: Product1[_]] =
    new StatementConstraint[?.type :: HNil, V] {}

  implicit def `?NodeTuple1`[L <: HList, V <: Product1[_]](implicit i: StatementConstraint[L, None.type]) =
    new StatementConstraint[?.type :: L, V] {}

  implicit def `?NodeTuple2`[L <: HList, V <: Product2[_, _]](implicit i: StatementConstraint[L, Product1[_]]) =
    new StatementConstraint[?.type :: L, V] {}

  implicit def HNilNothing =
    new StatementConstraint[HNil.type, None.type] {}

  implicit def stringHNilNothing =
    new StatementConstraint[String :: HNil, None.type] {}

  implicit def `*HNilNothing` =
    new StatementConstraint[*.type :: HNil, None.type] {}

  implicit def stringNodeAny[L <: HList, P <: Product](implicit i: StatementConstraint[L, P]) =
    new StatementConstraint[String :: L, P] {}

  implicit def stringNodeNothing[L <: HList](implicit i: StatementConstraint[L, None.type]) =
    new StatementConstraint[String :: L, None.type] {}

  implicit def `*NodeAny`[L <: HList, P <: Product](implicit i: StatementConstraint[L, P]) =
    new StatementConstraint[*.type :: L, P] {}

  implicit def `*NodeNothing`[L <: HList](implicit i: StatementConstraint[L, None.type]) =
    new StatementConstraint[*.type :: L, None.type] {}

  @implicitNotFound(msg = "Invalid Statement, the number of `?` should match the number of parameters")
  trait StatementConstraint[L, P <: Product] extends Serializable


}