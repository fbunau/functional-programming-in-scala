package _1_Introduction_to_functional_programming._3_Functional_data_structures

/*
  Normally this should be 'List'. And this should be a companion object to the List data type,
  but since we already defined List in the other file and since object is singleton and cannot be extended
  we must declare here a differently named object
*/
object MyList {

  def apply[A](as: A*): List[A] =
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

}

class CreatingListLiteralWithVariadicApply {

  def run(): Unit = {
    println("Constructing list with variadic apply on List companion object")

    println(
      s"""
         |A Constructing list with variadic apply on List companion object
         |${List.getClass.getCanonicalName}
         |${MyList.getClass.getCanonicalName}
      """.stripMargin)

    val l = MyList(1, 2, 3, 4)
    println(s"List: $l")
    println
  }

}
