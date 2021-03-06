package _1_Introduction_to_functional_programming._6_Purely_functional_state

import org.scalatest.matchers.should.Matchers
import org.scalatest.freespec.AnyFreeSpec
import scala.math.Ordering.Double.TotalOrdering

class SimpleRNGTest extends AnyFreeSpec with Matchers {

  private val seed = 42
  private val startRNG = SimpleRNG(seed)

  "Naive" - {

    "Random integer generation does not have side effects" in {
      startRNG.nextInt shouldBe SimpleRNG(seed).nextInt
    }

    "Different integers generated at different calls" in {
      val (x, rng1) = startRNG.nextInt
      val (y, _) = rng1.nextInt

      x should not be y
    }

    "Seed generates positive and negative int" in {
      val (x1, rng1) = startRNG.nextInt
      val (x2, rng2) = rng1.nextInt
      val (x3, _) = rng2.nextInt

      val generated = Set(x1, x2, x3)
      generated.count(_ < 0) should be > 0
      generated.count(_ >= 0) should be > 0
    }

    "Non negative int generation" in {
      val (x1, rngX1) = startRNG.nextInt
      val (x2, rngX2) = rngX1.nextInt
      val (x3, _) = rngX2.nextInt

      val (y1, rngY1) = RNG.nonNegativeInt(startRNG)
      val (y2, rngY2) = RNG.nonNegativeInt(rngY1)
      val (y3, _) = RNG.nonNegativeInt(rngY2)

      val generated_raw = Set(x1, x2, x3)
      val generated_positive = Set(y1, y2, y3)

      all(generated_positive) should be >= 0
      generated_raw.map(x => Math.abs(x)) shouldBe generated_positive
    }

    "Generating random double between 0 and 1, not including 1" in {
      val (x1, rng1) = RNG.doubleNaive(startRNG)
      val (x2, rng2) = RNG.doubleNaive(rng1)
      val (x3, rng3) = RNG.doubleNaive(rng2)
      val (x4, _) = RNG.doubleNaive(rng3)

      val generated_double = Set(x1, x2, x3, x4)
      generated_double.size shouldBe 4
      all(generated_double) should (be >= 0.0 and be < 1.0)
    }

    "Generating Int-Double pair" in {
      val (pair1, nextRNG) = RNG.intDoubleNaive(startRNG)
      val (pair2, _) = RNG.intDoubleNaive(nextRNG)

      val (i1, d1) = pair1
      val (i2, d2) = pair2

      i1 should not be i2
      d1 should not be d2

      all(List(d1, d2)) should (be >= 0.0 and be < 1.0)
    }

    "Generating Double-Int pair" in {
      val (pair1, nextRNG) = RNG.doubleIntNaive(startRNG)
      val (pair2, _) = RNG.doubleIntNaive(nextRNG)

      val (d1, i1) = pair1
      val (d2, i2) = pair2

      i1 should not be i2
      d1 should not be d2

      all(List(d1, d2)) should (be >= 0.0 and be < 1.0)
    }

    "Generating triplets of Double" in {
      val (triplet1, nextRNG) = RNG.double3(startRNG)
      val (triplet2, _) = RNG.double3(nextRNG)

      triplet1 should not be triplet2
    }

    "Generating a list of random integers" in {
      val (ints, _) = RNG.intsNaive(6)(startRNG)

      ints.distinct.size shouldBe 6
    }
  }

  "Generalized" - {

    "Generating random double between 0 and 1, not including 1" in {
      val (x1, rng1) = RNG.double(startRNG)
      val (x2, rng2) = RNG.double(rng1)
      val (x3, rng3) = RNG.double(rng2)
      val (x4, _) = RNG.double(rng3)

      val generated_double = Set(x1, x2, x3, x4)
      generated_double.size shouldBe 4
      all(generated_double) should (be >= 0.0 and be < 1.0)

    }

    "Generating Int-Double pair" in {
      val (pair1, nextRNG) = RNG.intDouble(startRNG)
      val (pair2, _) = RNG.intDouble(nextRNG)

      val (i1, d1) = pair1
      val (i2, d2) = pair2

      i1 should not be i2
      d1 should not be d2

      all(List(d1, d2)) should (be >= 0.0 and be < 1.0)
    }

    "Generating Double-Int pair" in {
      val (pair1, nextRNG) = RNG.doubleInt(startRNG)
      val (pair2, _) = RNG.doubleInt(nextRNG)

      val (d1, i1) = pair1
      val (d2, i2) = pair2

      i1 should not be i2
      d1 should not be d2

      all(List(d1, d2)) should (be >= 0.0 and be < 1.0)
    }

    "Generating a list of random integers" in {
      val (ints, _) = RNG.ints(6)(startRNG)

      ints.distinct.size shouldBe 6
    }

    "Generating non negative less than" in {
      val generated = RNG.sequence(List.fill(100)(RNG.nonNegativeLessThan(7)))(startRNG)

      generated._1.distinct.size shouldBe 7
      all(generated._1) should (be >= 0 and be < 7)
    }

    "Flat map a more general map" in {
      val generated = RNG.sequence(List.fill(100)(RNG.randPairViaFlatMaps(RNG.int, RNG.double)))(startRNG)
      val intDoublePairs = generated._1

      val (ints, doubles) = intDoublePairs.unzip

      ints.distinct.size shouldBe 100
      doubles.distinct.size shouldBe 100
      all(doubles) should (be >= 0.0 and be < 1.0)
    }

  }

}
