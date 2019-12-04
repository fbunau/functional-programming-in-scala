package _1_Introduction_to_functional_programming._2_Getting_started_with_functional_programming_in_Scala

import org.scalatest.matchers.should.Matchers
import org.scalatest.freespec.AnyFreeSpec

class PartialAppliedFunctionsTest extends AnyFreeSpec with Matchers {

  private val e = PartialAppliedFunctions()

  private val A = 4
  private val B = 5

  private def sum(a: Int, b: Int): Int = {
    a + b
  }

  "Partial applied functions with Theory implementation (A+B)" in {
    val sumWithA = e.partialLeft(A, sum)

    sumWithA(B) shouldBe 9
  }

  "Partial applied functions with with Scala lib (A+B)" in {
    val sumWithA = e.partialWithScalaLib(A, sum)

    sumWithA(B) shouldBe 9
  }
}
