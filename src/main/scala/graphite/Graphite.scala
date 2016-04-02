package graphite

import graphite.Node.{*, ?}
import shapeless.{::, HList, HNil}

import scala.annotation.implicitNotFound

object Graphite {

  @implicitNotFound(msg = "Invalid Statement, the number of `?` should match the number of parameters")
  trait ValueConstraint[L <: HList, P <: Product] extends Serializable

  case class Statement[P <: HList, V <: Product](path: P, values: V = None)(implicit vc: ValueConstraint[P, V])

  implicit def `?HNilTuple1`[V <: Product1[_]] = new ValueConstraint[?.type :: HNil, V] {}
  implicit def `?NodeTuple1`[L <: HList, V <: Product1[_]](implicit i: ValueConstraint[L, None.type]) = new ValueConstraint[?.type :: L, V] {}
  implicit def `?NodeTuple2`[L <: HList, V <: Product2[_, _]](implicit i: ValueConstraint[L, Product1[_]]) = new ValueConstraint[?.type :: L, V] {}

  implicit def stringHNilNothing = new ValueConstraint[String :: HNil, None.type] {}
  implicit def `*HNilNothing` = new ValueConstraint[*.type :: HNil, None.type] {}

  implicit def stringNodeAny[L <: HList, P <: Product](implicit i: ValueConstraint[L, P]) = new ValueConstraint[String :: L, P] {}
  implicit def stringNodeNothing[L <: HList](implicit i: ValueConstraint[L, None.type]) = new ValueConstraint[String :: L, None.type] {}
  implicit def `*NodeAny`[L <: HList, P <: Product](implicit i: ValueConstraint[L, P]) = new ValueConstraint[*.type :: L, P] {}
  implicit def `*NodeNothing`[L <: HList](implicit i: ValueConstraint[L, None.type]) = new ValueConstraint[*.type :: L, None.type] {}

}

