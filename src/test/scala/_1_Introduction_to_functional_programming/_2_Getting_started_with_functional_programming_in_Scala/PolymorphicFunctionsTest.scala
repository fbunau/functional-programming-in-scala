package _1_Introduction_to_functional_programming._2_Getting_started_with_functional_programming_in_Scala

import org.scalatest.matchers.should.Matchers
import org.scalatest.freespec.AnyFreeSpec

class PolymorphicFunctionsTest extends AnyFreeSpec with Matchers {

  private val e = PolymorphicFunctions()

  private val stringArray = Array("is", "Scala", "cool")
  private val intArray = Array(1, 2, 3, 4)

  "Searching for an existing String in a String array should return it's position" in {
    e.findFirst(stringArray, "Scala") shouldBe 1
  }

  "Searching for an non-existing String in a String array should return negative number, and use the most specialized function we have defined" in {
    e.findFirst(stringArray, "Java") shouldBe e.NotFound_SpecifiedType
  }

  "Searching for non-matching String predicate in a String array should use the polymporphic predicate function" in {
    e.findFirst(stringArray, (x: String) => x == "Java") shouldBe e.NotFound_GenericType_MatchByPredicate
  }

  "Searching for matching Integer predicate in an Integer array should use the polymporphic predicate function, and return the item position" in {
    e.findFirst(intArray, (x: Int) => x == 3) shouldBe 2
  }

  "Searching for non-matching Integer predicate in an Integer array should use the polymporphic predicate function" in {
    e.findFirst(intArray, (x: Int) => x == 7) shouldBe e.NotFound_GenericType_MatchByPredicate
  }

  "Searching for a non-existing Integer value in an Integer array should use the polymporphic find with value function" in {
    e.findFirst(intArray, 7) shouldBe e.NotFound_GenericType_MatchByEquals
  }

}
