package _1_Introduction_to_functional_programming._2_Getting_started_with_functional_programming_in_Scala

import org.scalatest.matchers.should.Matchers
import org.scalatest.freespec.AnyFreeSpec

class CurryFunctionsTest extends AnyFreeSpec with Matchers {

  private val e = CurryFunctions()

  private val A = 4
  private val B = 5

  private def sum(a: Int, b: Int) = {
    a + b
  }

  "Curry functions with Theory implementation (A+B)" in {
    val curriedSum = e.curry(sum)
    val curriedSum_A = curriedSum(A)

    curriedSum_A(B) shouldBe A + B
  }

  "Curry functions with Scala lib (A+B)" in {
    val curriedSum = e.curryWithScalaLib(sum)
    val curriedSum_A = curriedSum(A)

    curriedSum_A(B) shouldBe A + B
  }

  "Curry by defining the parameters with optional parameter list" in {

    val curriedAndAppliedFirstParameters = e.curryWithOptionalParameterList(1)(2) _
    val curriedAndAppliedAdditionalParameter = curriedAndAppliedFirstParameters(3)

    curriedAndAppliedFirstParameters(3)(4) shouldBe 10
    curriedAndAppliedAdditionalParameter(4) shouldBe 10
    e.curryWithOptionalParameterList(1)(2)(3)(4) shouldBe 10
  }

  "Uncurry functions (A+B)" in {
    val curriedSum = e.curry(sum)
    val uncurriedSum = e.uncurry(curriedSum)

    uncurriedSum(6, 13) shouldBe 19
  }
  
}
