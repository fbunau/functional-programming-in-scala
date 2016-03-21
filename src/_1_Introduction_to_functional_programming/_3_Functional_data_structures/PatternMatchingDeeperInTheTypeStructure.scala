package _1_Introduction_to_functional_programming._3_Functional_data_structures

class PatternMatchingDeeperInTheTypeStructure {

  def run(): Unit = {
    println(
      s"""
        |A more complex case of pattern matching
        |${List.getClass.getCanonicalName}
        |${MyList.getClass.getCanonicalName}
        |${ListWithOperations.getClass.getCanonicalName}
      """.stripMargin)

    val x = MyList(1,2,3,4,5) match {
      case Cons(x, Cons(2, Cons(4, _))) => x
      case Nil => 42
      case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
      case Cons(h, t) => h + ListWithOperations.sum(t)
      case _ => 101
    }

    println(s"X is : $x")
    println
  }

}
