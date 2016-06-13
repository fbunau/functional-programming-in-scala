package _2_Functional_design_and_combinator_libraries._7_Purely_functional_parallelism

import java.util.concurrent.{Executors, TimeUnit}

import _2_Functional_design_and_combinator_libraries._7_Purely_functional_parallelism.Par.Par
import org.scalatest.{FreeSpec, Matchers}

class ParTest extends FreeSpec with Matchers {

  val es = Executors.newFixedThreadPool(10)

  "Naive sum of list in parallel" in {
    val wses = Executors.newWorkStealingPool()

    Par.sumNaive(1 to 100)(wses).get shouldBe 5050
  }

  "Function can be evaluated asynchronously" in {
    def f(a: Int): Int = {
      42
    }

    val asyncF = Par.asyncF(f)(1)(es)

    asyncF.get shouldBe 42
  }

  "Sort a parallel computation" in {
    val l: Par[List[Int]] = Par.lazyUnit(List(8, 1, 2, 7, 9, 23, 1, 5, 2))

    Par.sortPar(l)(es).get shouldBe List(1, 1, 2, 2, 5, 7, 8, 9, 23)
  }

  "Map parallel computation" in {
    Par.parMap(List(1, 2, 3, 4))(_ * 2)(es).get shouldBe List(2, 4, 6, 8)
  }

  "Filter parallel computation" in {
    Par.parFilter(List(1, 2, 3, 4))(_ % 2 == 0)(es).get shouldBe List(2, 4)
  }

  "General sum of list in parallel" in {
    val wses = Executors.newWorkStealingPool()

    Par.sum(1 to 100)(wses).get shouldBe 5050
  }

  "General max of list in parallel" in {
    val wses = Executors.newWorkStealingPool()

    Par.max(Vector(8, 1, 2, 7, 9, 23, 1, 5, 2))(wses).get shouldBe 23
  }

}
