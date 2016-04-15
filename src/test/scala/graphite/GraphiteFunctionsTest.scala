package graphite

import graphite.GraphiteFunctions._
import shapeless.HNil

class GraphiteFunctionsTest extends GraphiteQueryTest {

  it("should have an average series of a metric") {
    averageSeries("stats" :: "probe" :: HNil)
  }

}
