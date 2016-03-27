package _1_Introduction_to_functional_programming._2_Getting_started_with_functional_programming_in_Scala

import org.scalatest.{FreeSpec, Matchers}

class PartialFunctionsTest extends FreeSpec with Matchers {

  val e = PartialFunctions()

  val A = 4
  val B = 5

  def sum(a: Int, b: Int) = {
    a + b
  }

  "Partial functions with Theory implementation (A+B)" in {
    val sumWithA = e.partialLeft(A, sum)

    sumWithA(B) shouldBe 9
  }

  "Partial functions with with Scala lib (A+B)" - {
    val sumWithA = e.partialWithScalaLib(A, sum)

    sumWithA(B) shouldBe 9
  }
}
