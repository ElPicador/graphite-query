package graphite

import graphite.Result._ //for the implicits
import graphite.Node._
import shapeless.HNil

class ResultTest extends GraphiteQueryTest {

  val entries = Seq.empty[GraphiteEntry]

  it("should compile for imports") {
    val statement = Statement(Query("a" :: * :: ? :: HNil), Tuple1("1"))
    statement(entries)
  }

  it("should compile a Statement with *") {
    """val s = Statement(Query("a" :: * :: HNil)); s(entries)""" should compile
  }

  it("should compile a Statement with a ? and a Tuple1") {
    """val s = Statement(Query("a" :: ? :: HNil), Tuple1("1")); s(entries)""" should compile
  }

  it("should compile a complex Path with a ? and a Tuple1") {
    """val s = Statement(Query("stats" :: "gauges" :: ? :: * :: "total-time" :: HNil), Tuple1("1")); s(entries)""" should compile
  }

  it("should compile a Path with two ? and a Tuple2") {
    """Statement(Query("a" :: ? :: ? :: HNil), ("1", "2"))""" should compile
  }


}
