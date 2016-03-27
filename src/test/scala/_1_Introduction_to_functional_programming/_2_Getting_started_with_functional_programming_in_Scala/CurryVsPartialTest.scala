package _1_Introduction_to_functional_programming._2_Getting_started_with_functional_programming_in_Scala

import org.scalatest.{FreeSpec, Matchers}

class CurryVsPartialTest extends FreeSpec with Matchers  {

  def compute(a: Int, b: Int, c: Int, d: Int): Int = {
    a + b - c + d
  }

  "Curry" in {
    val curriedCompute = (compute _).curried

    val curriedCompute_2 = curriedCompute(2)
    val curriedCompute_2_3_4 = curriedCompute_2(3)(4)

    assertDoesNotCompile("curriedCompute_2(3, 4)")
    curriedCompute_2_3_4(5) shouldBe 6
  }

  "Partial" in {
    val partialCompute = compute(2, _: Int, 4, _: Int)

    assertDoesNotCompile("computePartial(3)")
    partialCompute(3, 5) shouldBe 6
  }

}
