package graphite

import graphite.Graphite.Statement
import graphite.Node.{*, ?}
import org.scalatest.{FunSpec, Matchers}
import shapeless.HNil

class GraphiteTest extends FunSpec with Matchers {

  describe("implicit & type safety") {

    it("should compile a Path(String, *)") {
      """Statement("a" :: * :: HNil)""" should compile
    }

    it("should compile a Path(*, String)") {
      """Statement(* :: "a" :: HNil)""" should compile
    }

    it("should compile a Path with a ? and a Tuple1") {
      """Statement("a" :: ? :: HNil, Tuple1("1"))""" should compile
    }

    it("should compile a complex Path with a ? and a Tuple1") {
      """Statement("stats" :: "gauges" :: ? :: * :: "total-time" :: HNil, Tuple1("1"))""" should compile
    }

    it("should compile a Path with two ? and a Tuple2") {
      """Statement("a" :: ? :: ? :: HNil, ("1", "2"))""" should compile
    }

    it("should not compile a Path with a ? and no Tuple") {
      """Statement(? :: HNil)""" shouldNot compile
    }

    it("should not compile another Path with a ? and no Tuple") {
      """Statement(? :: * :: HNil)""" shouldNot compile
    }

    it("should not compile a Path with a ? and a Tuple2") {
      """Statement(? :: * :: HNil, (1, 2))""" shouldNot compile
    }

    it("should not compile a Path with two ? and a Tuple1") {
      """Statement(? :: * :: ? :: HNil, Tuple1(1))""" shouldNot compile
    }



  }

}
