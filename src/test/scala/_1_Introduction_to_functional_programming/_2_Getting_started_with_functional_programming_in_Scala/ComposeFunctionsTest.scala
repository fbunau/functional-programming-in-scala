package _1_Introduction_to_functional_programming._2_Getting_started_with_functional_programming_in_Scala

import org.scalatest.{FreeSpec, Matchers}

class ComposeFunctionsTest extends FreeSpec with Matchers {

  val e = ComposeFunctions()

  val double = (x: Int) => x * 2
  val addFive = (x: Int) => x + 5

  "Composing two arithmetic functions using theory implementation" in {
    val doubleAndAddFive = e.compose(double, addFive)
    doubleAndAddFive(2) shouldBe 9
  }

  "Composing two arithmetic functions using Scala lib implementation" in {
    val doubleAndAddFive = e.composeWithScalaLib(double, addFive)
    doubleAndAddFive(2) shouldBe 9
  }

}
