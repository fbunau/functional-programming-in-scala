package _1_Introduction_to_functional_programming._3_Functional_data_structures

import org.scalatest.{FreeSpec, Matchers}

class ListTest extends FreeSpec with Matchers {

  "Generic List implementation" - {

    "Test uses our defined list, and not the one from the Scala lib" in {
      List.getClass.getCanonicalName shouldBe "_1_Introduction_to_functional_programming._3_Functional_data_structures.List$"
    }

    "Assigning" - {

      "Compile error" - {

        "a list with mixed String and Integer elements to a String typed list" in {
          assertDoesNotCompile("val l: List[String] = Cons(\"a\", Cons(1, Nil))")
        }

        " a list with an Integer element to a String typed list" in {
          assertDoesNotCompile("val l: List[String] = Cons(\"a\", l2)")
        }

      }

      "Compile is OK" - {

        "Nil to a Double typed list" in {
          assertCompiles("val l: List[Double] = Nil")
        }

        "a list with a single Integer element to a Integer typed list" in {
          assertCompiles("val l: List[Double] = Cons(1, Nil)")
        }

        "a list with String elements to a String typed list" in {
          assertCompiles("val l: List[String] = Cons(\"a\", Cons(\"b\", Nil))")
        }

        "a list with mixed Integer and String elements to an Any typed list" in {
          assertCompiles("val l: List[Any] = Cons(\"a\", Cons(1, Nil))")
        }

        "a list with mixed Integer and String element to an an unspecified typed list" in {
          assertCompiles("val l = Cons(\"a\", Cons(1, Nil))")
        }

        "a list with String elements to an unspecified typed list" in {
          assertCompiles("val l = Cons(\"a\", Cons(\"b\", Nil))")
        }

      }
    }

    "Chaining lists" - {

      "Unspecified typed list with String elements chained with Integer element, assigned to unspecified typed list" in {
        val lstr = Cons("a", Cons("b", Nil))
        val lmixed = Cons(1, lstr)

        lmixed shouldBe Cons(1, Cons("a", Cons("b", Nil)))
      }

      "String typed list with String elements chained with Integer element, assigned to unspecified typed list" in {
        val lstr: List[String] = Cons("a", Cons("b", Nil))
        val lmixed = Cons(1, lstr)

        lmixed shouldBe Cons(1, Cons("a", Cons("b", Nil)))
      }

      "String typed list with String elements chained with Integer element, assigned to String typed list should not compile" in {
        val lstr: List[String] = Cons("a", Cons("b", Nil))
        assertDoesNotCompile("val lstr2: List[String] = Cons(1, lstr)")
      }

      "String typed list with String elements chained with String element, assigned to String typed list" in {
        val lstr: List[String] = Cons("a", Cons("b", Nil))
        val lstr2: List[String] = Cons("x", lstr)

        lstr2 shouldBe Cons("x", Cons("a", Cons("b", Nil)))
      }

    }

    "Creating list using List literal" in {
      List(1, 2, 3, 4) shouldBe Cons(1, Cons(2, Cons(3, Cons(4, Nil))))
    }
  }

  "List operations implemented in companion object" - {

    "Simple list operations using pattern matching" - {

      "Tail" - {
        "Tail of a non empty list" in {
          List.tail(List(1, 2, 3, 4, 5, 6)) shouldBe List(2, 3, 4, 5, 6)
        }

        "Tail of am empty list" in {
          List.tail(List()) shouldBe Nil
        }
      }

      "Set head" - {
        "Set head on a non-empty list" - {
          List.setHead(List(1, 2, 3, 4), 9) shouldBe List(9, 2, 3, 4)
        }

        "Set head on an empty list" - {
          List.setHead(List(), 9) shouldBe List(9)
        }
      }

      "Drop first n elements" - {

        "Drop some elements from a non empty list" in {
          List.drop(List(1, 2, 3, 4, 5), 3) shouldBe List(4, 5)
        }

        "Drop all elements from a non empty list" in {
          List.drop(List(1, 2, 3, 4, 5), 5) shouldBe List()
        }

        "Drop more nb of elements from a non empty list" in {
          List.drop(List(1, 2, 3, 4, 5), 10) shouldBe List()
        }

        "Drop elements from an empty list" in {
          List.drop(List(), 10) shouldBe List()
        }

      }

      "Append two lists" - {

        "Append non-empty list to non-empty list" in {
          List.append(List(1, 2, 3, 4), List(5, 6, 7, 8, 9)) shouldBe List(1, 2, 3, 4, 5, 6, 7, 8, 9)
        }

        "Append empty list to non-empty list" in {
          List.append(List(1, 2, 3, 4), List()) shouldBe List(1, 2, 3, 4)
        }

        "Append non-empty list to empty list" in {
          List.append(List(), List(1, 2, 3, 4)) shouldBe List(1, 2, 3, 4)
        }

        "Append empty list to empty list" in {
          List.append(List(), List()) shouldBe List()
        }

      }

    }

    "Recursive simple implementations using pattern matching" - {

      "Sum on an Integer list" in {
        val l = List(1, 2, 3, 4, 5)
        List.sumNaive(l) shouldBe 15
      }

      "Product on an Integer list should not compile" in {
        val l = List(1, 2, 3, 4, 5)
        assertDoesNotCompile("val product = List.product(l)")
      }

      "Product on a Double list" in {
        val l: List[Double] = Cons(1, Cons(2, Cons(3, Cons(4, Cons(5, Nil)))))
        List.productNaive(l) shouldBe 120
      }

    }

    "Generalized implementations using higher order functions" - {
      "Sum and product using foldRight" - {

        "Sum on an Integer list" in {
          val l = List(1, 2, 3, 4, 5)
          List.sum(l) shouldBe 15
        }

        "Product on a Double list" in {
          val l: List[Double] = Cons(1, Cons(2, Cons(3, Cons(4, Cons(5, Nil)))))
          List.product(l) shouldBe 120
        }

      }
    }
  }

  "Pattern matching deeper into the type structure" in {
    val r = List(1, 2, 3, 4, 5) match {
      case Cons(x, Cons(2, Cons(4, _))) => x
      case Nil => 42
      case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
      case Cons(h, t) => h + List.sumNaive(t)
      case _ => 101
    }

    r shouldBe 3
  }

  "Type inference is not complete, but can be helped" - {

    "Type inference in Scala is not complete, and sometimes it cannot infer parameter types" in {
      val xs: List[Int] = List(1, 2, 3, 4, 5)
      assertDoesNotCompile("List.dropWhile(xs, x => x < 4)")
    }

    "Specifying the parameter types will tell the compiler exactly what it should call" in {
      val xs: List[Int] = List(1, 2, 3, 4, 5)
      List.dropWhile(xs, (x: Int) => x < 4) shouldBe List(4, 5)
    }

    "Type inference can be helped by currying the function with optional parameters list" in {
      val xs: List[Int] = List(1, 2, 3, 4, 5)
      List.dropWhileEnhanced(xs)(x => x < 4) shouldBe List(4, 5)
    }

  }

}