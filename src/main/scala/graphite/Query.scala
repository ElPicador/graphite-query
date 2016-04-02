//package graphite
//
//sealed trait Query {
//  type P <: Path
//
//  val path: P
//  val aggregation: Option[String]
//
//  //  def apply[V <: Product](values: V): Statement[P, Query[P], V] = ???
//}
//
//sealed trait QueryHolder[P <: Path, Q <: Query] {
//
//  def apply(path: P, aggregation: Option[String] = None): Q
//
//}
//
//object Query {
//
//  def apply[PATH <: Path](p: PATH, a: Option[String] = None) = new Query {
//    type P = PATH
//
//    override val path = p
//    override val aggregation = a
//  }
//
//  implicit object q11 extends QueryHolder[Path1, Query1] {
//    override def apply(path: Path1, aggregation: Option[String] = None): Query1 = Query1(path, aggregation)
//  }
//
//}
//
////case class Query0(path: Path0, aggregation: Option[String] = None) extends Query {
////  type P = Path0
////}
//
//case class Query1(path: Path1, aggregation: Option[String] = None) extends Query {
//  type P = Path1
//}