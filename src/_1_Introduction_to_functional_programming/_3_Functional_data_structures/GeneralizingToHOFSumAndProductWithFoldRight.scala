package _1_Introduction_to_functional_programming._3_Functional_data_structures

class GeneralizingToHOFSumAndProductWithFoldRight {

  def foldRight[A,B](as: List[A], z: B)(f: (A, B) => B): B = as match {
    case Nil => z
    case Cons(x, xs) => f(x, foldRight(xs, z)(f))
  }

  def sum(ns: List[Int]) =
    foldRight(ns, 0)((x,y) => x + y)

  def product(ns: List[Double]) =
    foldRight(ns, 1.0)(_ * _)

  def run(): Unit = {

    val l1 = MyList(1, 2, 3, 4)
    val l2: List[Double] = MyList(1, 2, 3, 4)

    println(
      s"""
         |Sum and Product operations implemented using common logic extracted into HOF
         |
         |l1      : $l1
         |l2      : $l2
         |sum     : ${sum(l1)}
         |product : ${product(l2)}
      """.stripMargin)
  }
}
