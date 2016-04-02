//package graphite
//
//sealed trait Statement[P <: Path, +Q <: Query, V <: Product] {
//  val query: Q
//  val values: V
//}
//
////case class Statement0[T](query: Query0, values: T) extends Statement {
////  type Q = Query0
////  type V = T
////}
////
//private[graphite] sealed trait StatementHolder[P <: Path, Q <: Query, V <: Product, S <: Statement[P, Q, V]] {
//
//  def apply(q: Q, v: V): S
//
//}
//
//object Statement {
//
////  def apply[Q <: Query, V <: Product](q: Q, v: V) = new Statement {
////    override type QUERY = Q
////    override type VALUES = V
////
////    val query = q
////    val values = v
////  }
//
//  def apply[P <: Path, Q <: Query, V <: Product, S <: Statement[P, Q, V]](query: Q, values: V)(implicit holder: StatementHolder[P, Q, V, S]): S = holder(query, values)
//
//  implicit object s01 extends StatementHolder[Path1, Query1, Product1[_], Statement[Path1, Query1, Product1[_]]] {
//    override def apply(q: Query1, v: Product1[_]): Statement[Path1, Query1, Product1[_]] = new Statement[Path1, Query1, Product1[_]] {
//      override val query: Query1 = q
//      override val values: Product1[_] = v
//    }
//  }
//
//
//}