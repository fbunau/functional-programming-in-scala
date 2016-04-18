package _1_Introduction_to_functional_programming._5_Strictness_and_laziness

import org.scalatest.{FreeSpec, Matchers}

class StreamTest extends FreeSpec with Matchers {

  "Stream constructed by hand, lazy element not evaluated" in {
    var evaluated = 0
    def lazyElement: Int = {
      evaluated += 1
      10
    }

    val stream = Stream.cons(1, Stream.cons(2, Stream.cons(lazyElement, Empty)))
    evaluated shouldBe 0
    stream.toList shouldBe List(1, 2, 10)
    evaluated shouldBe 1
  }

  "Stream using smart constructor not working ? Why is lazy element evaluated" in {
    var evaluated = 0
    def lazyElement: Int = {
      evaluated += 1
      10
    }

    //
    val stream = Stream(1, 2, lazyElement)
    evaluated shouldBe 1 // ! Not 0 because variadic apply constructor will force the stream to be built
    stream.toList shouldBe List(1, 2, 10)
    evaluated shouldBe 1
  }

  "Stream can be converted to a List" in {
    Stream(1, 2, 3, 4).toList shouldBe List(1, 2, 3, 4)
  }

  "Can take first elements from a Stream" in {
    Stream(1, 2, 3, 4, 5, 6, 7).take(4).toList shouldBe List(1, 2, 3, 4)
  }

  "Can take first elements from a Stream lazily excluding lazy element, leaving untaken element unevaluated" in {
    var evaluated = 0
    def lazyElement: Int = {
      evaluated += 1
      10
    }

    val stream = Stream.cons(1, Stream.cons(2, Stream.cons(3, Stream.cons(4, Stream.cons(5, Stream.cons(lazyElement, Stream.cons(6, Stream.cons(7, Stream.cons(8, Empty)))))))))

    stream.take(4).toList shouldBe List(1, 2, 3, 4)
    evaluated shouldBe 0
  }

  "Can take first elements from a Stream lazily, including lazy element, leaving untaken element unevaluated" in {
    var evaluated = 0
    def lazyElement: Int = {
      evaluated += 1
      10
    }

    val stream = Stream.cons(1, Stream.cons(2, Stream.cons(3, Stream.cons(4, Stream.cons(5, Stream.cons(lazyElement, Stream.cons(6, Stream.cons(7, Stream.cons(8, Empty)))))))))

    val takenStream = stream.take(6)
    evaluated shouldBe 0

    takenStream.toList shouldBe List(1, 2, 3, 4, 5, 10)
    evaluated shouldBe 1
  }

  "Can drop first elements from a Stream" in {
    Stream(1, 2, 3, 4, 5, 6, 7).drop(4).toList shouldBe List(5, 6, 7)
  }

  "Can take while condition is true" in {
    Stream(1, 2, 3, 4, 5, 6, 7).takeWhile(x => x % 2 == 0).toList shouldBe List(2, 4, 6)
  }

  "Can take while condition is true, with lazy element in stream" in {
    var evaluated = 0
    def lazyElement: Int = {
      evaluated += 1
      10
    }

    val stream = Stream.cons(1, Stream.cons(2, Stream.cons(3, Stream.cons(4, Stream.cons(5, Stream.cons(lazyElement, Stream.cons(6, Stream.cons(7, Stream.cons(8, Empty)))))))))
    val filteredStream = stream.takeWhile(x => x % 2 == 0)
    evaluated shouldBe 0

    filteredStream.toList shouldBe List(2, 4, 10, 6, 8)
    evaluated shouldBe 1
  }

  "Value exists in stream" in {
    Stream(1, 2, 3, 4, 5).exists(x => x == 3) shouldBe true
  }

  "Value does not exist in stream" in {
    Stream(1, 2, 3, 4, 5).exists(x => x == 10) shouldBe true
  }

  "Value exists in stream and lazy value is not evaluated" in {
    var evaluated = 0
    def lazyElement: Int = {
      evaluated += 1
      10
    }

    val stream = Stream.cons(1, Stream.cons(2, Stream.cons(3, Stream.cons(4, Stream.cons(5, Stream.cons(lazyElement, Stream.cons(6, Stream.cons(7, Stream.cons(8, Empty)))))))))

    stream.exists(x => x == 3) shouldBe true
    evaluated shouldBe 0
  }

  "Taking finite amount from infinite stream results in a finite list" in {
    Stream.constant(5).take(4).toList shouldBe List(5, 5, 5, 5)
  }

}
