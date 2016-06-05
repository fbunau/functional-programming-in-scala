package problems

import org.scalatest.{FreeSpec, Matchers}

import scala.collection.mutable.ListBuffer

/**
  * We want to generate all subsets of the word, such that:
  * For "abcd" the result would be :
  * "", "a", "b", "ab", "c", "ac", "bc", "abc", "d", "ad", "bd", "abd", "cd", "acd", "bcd", "abcd"
  *
  * Given:
  * a Stream of Char
  *
  * Expected
  * a Stream of Strings
  *
  * Can we consume the input stream lazily with just enough data to produce only the amount of results we need
  * according to the statement above
  */
class LazyOrderedSubsets extends FreeSpec with Matchers {

  private def solution(word: Stream[Char]) =
    word.scanLeft((Stream(""), Stream(""))) ((acc, l)=> {
      val r = acc._2.map(_ + l)
      (r, acc._2 append r)
    }).flatMap(_._1)

  "Taking 5 elements from the result should evaluate only 3 elements from the initial stream" in {

    val usedElements =  ListBuffer[Char]()

    solution(
      Stream('a', 'b', 'c', 'd', 'e', 'f').map(
        x => {
          usedElements += x
          x
        }
      )
    ).take(5).toList shouldBe List("", "a", "b", "ab", "c")

    // solution is 1 element greedier than optimal
    usedElements.toList shouldBe List('a', 'b', 'c', 'd')
  }

}
