package _2_Functional_design_and_combinator_libraries._7_Purely_functional_parallelism

import org.scalatest.{FreeSpec, Matchers}

class ParallelTest extends FreeSpec with Matchers {

  "Test sum of list" in {
    Parallel.sum(Vector(1, 2, 3, 9, 10, 11, 42)) shouldBe 78
  }

}
