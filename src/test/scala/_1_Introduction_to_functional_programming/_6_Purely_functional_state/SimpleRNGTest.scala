package _1_Introduction_to_functional_programming._6_Purely_functional_state

import org.scalatest.{FreeSpec, Matchers}

class SimpleRNGTest extends FreeSpec with Matchers {

  val seed = 1111111111

  "Random integer generation does not have side effects" in {
    SimpleRNG(seed).nextInt shouldBe SimpleRNG(seed).nextInt
  }

  "Different integers generated at different calls" in {
    val (x, rng1) = SimpleRNG(seed).nextInt
    val (y, rng2) = rng1.nextInt

    x should not be y
  }

  "Seed generates first a negative int" in {
    val (x, rng) = SimpleRNG(seed).nextInt
    x should be < 0
  }

  "Non negative int generation" in {
    val startRNG = SimpleRNG(seed)

    val (x1, rngX1) = startRNG.nextInt
    val (x2, rngX2) = rngX1.nextInt
    val (x3, rngX3) = rngX2.nextInt

    val (y1, rngY1) = RNG.nonNegativeInt(startRNG)
    val (y2, rngY2) = RNG.nonNegativeInt(rngY1)
    val (y3, rngY3) = RNG.nonNegativeInt(rngY2)

    val generated_raw = Set(x1, x2, x3)
    val generated_positive = Set(y1, y2, y3)

    generated_positive.filter(x => x >= 0) shouldBe generated_positive
    generated_raw.map(x => Math.abs(x)) shouldBe generated_positive
  }



}
