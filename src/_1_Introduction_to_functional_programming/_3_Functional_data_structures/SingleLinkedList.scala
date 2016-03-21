package _1_Introduction_to_functional_programming._3_Functional_data_structures

sealed trait List[+A]
case object Nil extends List[Nothing]
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {

}

class SingleLinkedList {

  def run(): Unit = {
    println("Using the data constructors of List to create lists")
    println(List.getClass.getCanonicalName)

    val l1: List[Double] = Nil
    val l2: List[Int] = Cons(1, Nil)
    val l3: List[String] = Cons("a", Cons("b", Nil))
    //val l4: List[String] = Cons("a", Cons(1, Nil)) does not compile
    // val l4: List[String] = Cons("a", l2) does not compile
    val l4: List[Any] = Cons("a", Cons(1, Nil))
    val l5 = Cons("a", Cons(1, Nil))
    val l6 = Cons("a", Cons("b", Nil))
    val l7 = Cons(1, l6)
    val l8 = Cons(1, l3)
    val l9: List[String] = Cons("a", l3)

    println(
      s"""
         |l1: $l1
         |l2: $l2
         |l3: $l3
         |l4: $l4
         |l5: $l6
         |l6: $l6
         |l7: $l7
         |l8: $l8
         |l9: $l9
      """.stripMargin)
  }

}
