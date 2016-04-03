package graphite

import graphite.Node.{*, ?}
import org.scalatest.{FunSpec, Matchers}
import shapeless.HNil

class StatementTest extends FunSpec with Matchers {

  describe("implicit & type safety") {

    it("should compile for imports") {
      Statement(Query("a" :: * :: ? :: HNil), Tuple1("1"))
    }

    it("should compile a Path(String, *)") {
      """Statement(Query("a" :: * :: HNil))""" should compile
    }

    it("should compile a Path(*, String)") {
      """Statement(Query(* :: "a" :: HNil))""" should compile
    }

    it("should compile a Path with a ? and a Tuple1") {
      """Statement(Query("a" :: ? :: HNil), Tuple1("1"))""" should compile
    }

    it("should compile a complex Path with a ? and a Tuple1") {
      """Statement(Query("stats" :: "gauges" :: ? :: * :: "total-time" :: HNil), Tuple1("1"))""" should compile
    }

    it("should compile a Path with two ? and a Tuple2") {
      """Statement(Query("a" :: ? :: ? :: HNil), ("1", "2"))""" should compile
    }

    it("should not compile a Path with a ? and no Tuple") {
      """Statement(Query(? :: HNil))""" shouldNot compile
    }

    it("should not compile another Path with a ? and no Tuple") {
      """Statement(Query(? :: * :: HNil))""" shouldNot compile
    }

    it("should not compile a Path with a ? and a Tuple2") {
      """Statement(Query(? :: * :: HNil), (1, 2))""" shouldNot compile
    }

    it("should not compile a Path with two ? and a Tuple1") {
      """Statement(Query(? :: * :: ? :: HNil), Tuple1(1))""" shouldNot compile
    }

  }

}
