package _1_Introduction_to_functional_programming._2_Getting_started_with_functional_programming_in_Scala

import org.scalatest.{FreeSpec, Matchers}

class FibonacciWithTailRecursionTest extends FreeSpec with Matchers {
  val e = FibonacciWithTailRecursion()

  "Fibonacci element in sequence" in {
    e.fib(10) shouldBe 55
  }
}
