package utils


//sealed trait AshList
//
//trait AshNil extends AshList {
//
//  def :#:[A](a: A): AshList = utils.:#:(a, this)
//
//}
//
//case object AshNil extends AshNil
//
//case class :#:[+H, +T <: AshList](head: H, tail: T) extends AshList
//
//object AshList {
//
//  def apply() = AshNil
//
//  def apply[T](t: T) = t :#: AshNil
//
//}
//
//object toto {
//
//  val a: String :#: Int = "1" :#: 1
//  val b: Int :#: String = 1 :#: "1"
//  val c: Int :#: String :#: Double = 1 :#: "1" :#: 1.0
//
//  implicit class AnyToAshList[T](tail: T) {
//    def :#:[H](head: H) = utils.:#:(head, tail)
//  }
//
//}

object tata {
  import shapeless.HNil

  val a = 1 :: "1" :: HNil


}