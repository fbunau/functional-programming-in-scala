package _1_Introduction_to_functional_programming._3_Functional_data_structures

/*
  Normally this should be 'List'. And this should be a companion object to the List data type,
  but since we already defined List in the other file and since object is singleton and cannot be extended
  we must declare here a differently named object
*/
object ListWithOperations {

  def sum(ints: List[Int]): Int = ints match {
    case Nil => 0
    case Cons(x, xs) => x + sum(xs)
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x, xs) => x * product(xs)
  }

}

class PatternMatchingToCreateListOperations {

  def run(): Unit = {
    println("Implementation of list operations using pattern matching")
    println(List.getClass.getCanonicalName)
    println(ListWithOperations.getClass.getCanonicalName)

    val l1 = Cons(1, Cons(2, Cons(3, Cons(4, Cons(5, Nil)))))
    val l2: List[Double] = Cons(1, Cons(2, Cons(3, Cons(4, Cons(5, Nil)))))

    val sum = ListWithOperations.sum(l1)
    //val product = List.product(l1) does not compile
    val product = ListWithOperations.product(l2)

    println(
      s"""
         |l1            : $l1
         |Sum of l1     : $sum
         |l2            : $l2
         |Product of l2 : $product
      """.stripMargin)
  }

}
