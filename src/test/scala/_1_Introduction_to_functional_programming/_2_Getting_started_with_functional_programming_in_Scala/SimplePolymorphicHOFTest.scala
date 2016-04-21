package _1_Introduction_to_functional_programming._2_Getting_started_with_functional_programming_in_Scala

import org.scalatest.{FreeSpec, Matchers}

class SimplePolymorphicHOFTest extends FreeSpec with Matchers {

  val e = SimplePolymorphicHOF()

  val names = List("Bil", "Ana", "Gigi", "Radu", "Gheorghe")
  val integers = List(1, 2, 3, 4, 5, 6, 7)

  "Ordered list is identified as sorted by length of name" in {
    e.isSorted(names, (x: String, y: String) => x.length > y.length) shouldBe true
  }

  "Ordered list is identified as unsorted by string comparison" in {
    e.isSorted(names, (x: String, y: String) => x > y) shouldBe false
  }

  "We can check sorted also on list of integers" in {
    e.isSorted(integers, (x: Int, y: Int) => x > y) shouldBe true
  }

}
