package _1_Introduction_to_functional_programming._3_Functional_data_structures

import org.scalatest.matchers.should.Matchers
import org.scalatest.freespec.AnyFreeSpec

class ScalaCollectionLibraryExamples extends AnyFreeSpec with Matchers {

  "Operations between the elements" - {

    "scanLeft - like foldLeft with intermediate results" in {
      scala.List(1, 2, 3).scanLeft(100)((z, n) => z - n) shouldBe scala.List(100, 99, 97, 94)
    }

    "scanRight - like foldRight with intermediate results" in {
      scala.List(1, 2, 3).scanRight(100)((n, z) => n - z) shouldBe scala.List(-98, 99, -97, 100)
    }

    "reduceLeft - like foldLeft, but z is leftmost element" in {
      scala.List(1, 2, 3).reduceLeft((z, n) => z - n) shouldBe -4
    }

    "reduceRight - like foldRight, but z is righmost element" in {
      scala.List(1, 2, 3).reduceRight((n, z) => z - n) shouldBe 0
    }
  }

  "Generating sub-collection" - {

    "init - All except the last element" in {
      scala.List(1, 2, 3).init shouldBe scala.List(1, 2)
    }

    "tail - All except, the first element" in {
      scala.List(1, 2, 3).tail shouldBe scala.List(2, 3)
    }

    "inits - Sequentially except for the last" in {
      scala.List(1, 2, 3).inits.toList shouldBe scala.List(
        scala.List(1, 2, 3), scala.List(1, 2), scala.List(1), scala.List()
      )
    }

    "tails - Sequentially except for the first" in {
      scala.List(1, 2, 3).tails.toList shouldBe scala.List(
        scala.List(1, 2, 3), scala.List(2, 3), scala.List(3), scala.List()
      )
    }

    "take - First N of the collection" in {
      scala.List(1, 2, 3, 4, 5).take(2) shouldBe scala.List(1, 2)
    }

    "takeRight - Last N of the collection" in {
      scala.List(1, 2, 3, 4, 5).takeRight(2) shouldBe scala.List(4, 5)
    }

    "drop - Except the first N of the collection" in {
      scala.List(1, 2, 3, 4, 5).drop(2) shouldBe scala.List(3, 4, 5)
    }

    "dropRight - Except the last N of the collection" in {
      scala.List(1, 2, 3, 4, 5).dropRight(2) shouldBe scala.List(1, 2, 3)
    }

    "slice - Subscript range [a, b)" in {
      scala.List(1, 2, 3, 4, 5).slice(1, 4) shouldBe scala.List(2, 3, 4)
    }

    "splitAt - Split at position k : [.., k) [k, ..]" in {
      scala.List(1, 2, 3, 4, 5).splitAt(2) shouldBe (scala.List(1, 2), scala.List(3, 4, 5))
    }

  }

  "Generating sub-collection by predicate" - {

    "takeWhile - Take from the head of the collection, terminate when predicate is false" in {
      scala.List(1, 3, 2, 5).takeWhile(n => n % 2 == 1) shouldBe scala.List(1, 3)
    }

    "dropWhile - Drop from the tail of the collection, terminate when predicate is false" in {
      scala.List(1, 3, 2, 5).dropWhile(n => n % 2 == 1) shouldBe scala.List(2, 5)
    }

  }

  "Global predicate on all elements" - {

    "forall - All elements satisfy a condition" in {
      scala.List(1, 2, 3).forall(n => n < 4) shouldBe true
    }

    "exists - Atleast one element satisfies a condition" in {
      scala.List(1, 2, 3).exists(n => n % 2 == 0) shouldBe true
    }

    "sameElements - Elements are equals with another collection (order is important)" in {
      Array(1, 2, 3).sameElements(scala.List(1L, 2L, 3L)) shouldBe true
      Array(1, 2, 3).sameElements(scala.List(2L, 1L, 3L)) shouldBe false
      Array(1, 2, 3).sameElements(scala.List(1L, 2L, 4L)) shouldBe false
    }

  }
}
