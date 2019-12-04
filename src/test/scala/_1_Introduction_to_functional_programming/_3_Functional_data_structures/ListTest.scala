package _1_Introduction_to_functional_programming._3_Functional_data_structures

import org.scalatest.matchers.should.Matchers
import org.scalatest.freespec.AnyFreeSpec

class ListTest extends AnyFreeSpec with Matchers {

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
        val _: List[String] = Cons("a", Cons("b", Nil))
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
        "Set head on a non-empty list" in {
          List.setHead(List(1, 2, 3, 4), 9) shouldBe List(9, 2, 3, 4)
        }

        "Set head on an empty list" in {
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

      "Return a list with all but the last elements of a list" - {

        "On non empty list" in {
          List.init(List(1, 2, 3, 4)) shouldBe List(1, 2, 3)
        }

        "On empty list" in {
          List.init(List()) shouldBe List()
        }

      }
    }

    "Recursive simple implementations using pattern matching" - {

      "Sum on an Integer list" in {
        val l = List(1, 2, 3, 4, 5)
        List.sumNaive(l) shouldBe 15
      }

      "Product on an Integer list should not compile" in {
        val _ = List(1, 2, 3, 4, 5)
        assertDoesNotCompile("val product = List.product(l)")
      }

      "Product on a Double list" in {
        val l: List[Double] = Cons(1, Cons(2, Cons(3, Cons(4, Cons(5, Nil)))))
        List.productNaive(l) shouldBe 120
      }

    }

    "Generalized implementations using higher order functions" - {
      "Sum, product, length using foldRight" - {

        "Sum on an Integer list" in {
          val l = List(1, 2, 3, 4, 5)
          List.sumRight(l) shouldBe 15
        }

        "Product on a Double list" in {
          val l: List[Double] = Cons(1, Cons(2, Cons(3, Cons(4, Cons(5, Nil)))))
          List.productRight(l) shouldBe 120
        }

        "Length" - {
          "Length of empty list" in {
            List.lengthRight(List()) shouldBe 0
          }

          "Length of non-empty list" in {
            List.lengthRight(List(1, 2, 3, 4)) shouldBe 4
          }
        }

        "Append two lists" - {

          "Append non-empty list to non-empty list" in {
            List.appendWithFoldRight(List(1, 2, 3, 4), List(5, 6, 7, 8, 9)) shouldBe List(1, 2, 3, 4, 5, 6, 7, 8, 9)
          }

          "Append empty list to non-empty list" in {
            List.appendWithFoldRight(List(1, 2, 3, 4), List()) shouldBe List(1, 2, 3, 4)
          }

          "Append non-empty list to empty list" in {
            List.appendWithFoldRight(List(), List(1, 2, 3, 4)) shouldBe List(1, 2, 3, 4)
          }

          "Append empty list to empty list" in {
            List.appendWithFoldRight(List(), List()) shouldBe List()
          }

        }

      }

      "Sum, product, length using foldLeft" - {

        "Sum on an Integer list" in {
          val l = List(1, 2, 3, 4, 5)
          List.sumLeft(l) shouldBe 15
        }

        "Product on a Double list" in {
          val l: List[Double] = Cons(1, Cons(2, Cons(3, Cons(4, Cons(5, Nil)))))
          List.productLeft(l) shouldBe 120
        }

        "Length" - {
          "Length of empty list" in {
            List.lengthLeft(List()) shouldBe 0
          }

          "Length of non-empty list" in {
            List.lengthLeft(List(1, 2, 3, 4)) shouldBe 4
          }
        }

        "Reverse" - {
          "Reverse non-empty list" in {
            List.reverse(List(1, 2, 3)) shouldBe List(3, 2, 1)
          }

          "Reverse singleton list" in {
            List.reverse(List(1)) shouldBe List(1)
          }

          "Reverse empty list" in {
            List.reverse(List()) shouldBe List()
          }
        }

        "Concat" - {
          "Empty list" in {
            List.concat(List()) shouldBe List()
          }

          "Mixed list of lists" in {
            List.concat(List( List(), List(1, 2), List(3, 4, 5), List(6), List(), List(7, 8))) shouldBe
              List(1, 2, 3, 4, 5, 6, 7, 8)
          }
        }

      }

      "Relationship between data constructor and foldRight" in {
        List.foldRight(List(1,2,3), Nil:List[Int])(Cons(_, _)) shouldBe List(1, 2, 3)
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
      val _: List[Int] = List(1, 2, 3, 4, 5)
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

  "Naive map functions" - {
    "Add one" - {
      "On empty list" in {
        List.naiveAddOne(List()) shouldBe List()
      }

      "On non-empty list" in {
        List.naiveAddOne(List(1, 2, 3)) shouldBe List(2, 3, 4)
      }
    }

    "Double to String" - {
      "On empty list" in {
        List.naiveDoubleToString(List()) shouldBe List()
      }

      "On non-empty list" in {
        List.naiveDoubleToString(List(1.0, 2.0, 3.0)) shouldBe List("1.0", "2.0", "3.0")
      }
    }
  }

  "Map" - {
    "Add one" - {
      "On empty list" in {
        List.map(List[Int]())(_ + 1) shouldBe List()
      }

      "On non-empty list" in {
        List.map(List(1, 2, 3))(_ + 1) shouldBe List(2, 3, 4)
      }
    }

    "Double to String" - {
      "On empty list" in {
        List.map(List[Int]())(_.toString) shouldBe List()
      }

      "On non-empty list" in {
        List.map(List(1.0, 2.0, 3.0))(_.toString) shouldBe List("1.0", "2.0", "3.0")
      }
    }
  }

  "Filter" - {
    "Recursive" - {
      "Filter on empty list" in {
        List.filterRecursive(List[Int]())(x => x > 2) shouldBe List()
      }

      "Filter on non-empty list, none match" in {
        List.filterRecursive(List(1, 2, 3))(x => x > 3) shouldBe List()
      }

      "Filter on non-empty list, all match" in {
        List.filterRecursive(List(1, 2, 3))(x => x > 0) shouldBe List(1, 2, 3)
      }

      "Filter on non-empty list, some match" in {
        List.filterRecursive(List(1, 2, 3))(x => x % 2 == 1) shouldBe List(1, 3)
      }
    }

    "Fold right" - {
      "Filter on empty list" in {
        List.filterFoldRight(List[Int]())(x => x > 2) shouldBe List()
      }

      "Filter on non-empty list, none match" in {
        List.filterFoldRight(List(1, 2, 3))(x => x > 3) shouldBe List()
      }

      "Filter on non-empty list, all match" in {
        List.filterFoldRight(List(1, 2, 3))(x => x > 0) shouldBe List(1, 2, 3)
      }

      "Filter on non-empty list, some match" in {
        List.filterFoldRight(List(1, 2, 3))(x => x % 2 == 1) shouldBe List(1, 3)
      }
    }

    "Flat map" - {
      "Filter on empty list" in {
        List.filter(List[Int]())(x => x > 2) shouldBe List()
      }

      "Filter on non-empty list, none match" in {
        List.filter(List(1, 2, 3))(x => x > 3) shouldBe List()
      }

      "Filter on non-empty list, all match" in {
        List.filter(List(1, 2, 3))(x => x > 0) shouldBe List(1, 2, 3)
      }

      "Filter on non-empty list, some match" in {
        List.filter(List(1, 2, 3))(x => x % 2 == 1) shouldBe List(1, 3)
      }
    }
  }

  "Flat map" - {
    "Doubling elements in a list" in {
      List.flatMap(List(1, 2, 3))(i => List(i, i)) shouldBe List(1, 1, 2, 2, 3, 3)
    }
  }

  "Add two int lists" - {
    "Naive" in {
      List.addTwoIntLists(List(1, 2, 3), List(4, 5, 6)) shouldBe List(5, 7, 9)
    }

    "Zip with" in {
      List.zipWith(List(1, 2, 3), List(4, 5, 6))(_ + _) shouldBe List(5, 7, 9)
    }
  }

  "Has subsequence" in {
    List.hasSubsequence(List(1, 2, 3, 4), List(1, 2)) shouldBe true
    List.hasSubsequence(List(1, 2, 3, 4), List(2, 3)) shouldBe true
    List.hasSubsequence(List(1, 2, 3, 4), List(4)) shouldBe true
    List.hasSubsequence(List(1, 2, 3, 4), List(2, 4)) shouldBe false
  }



}