package _1_Introduction_to_functional_programming._2_Getting_started_with_functional_programming_in_Scala

class PolymorphicFunctions {

  val NotFound_SpecifiedType = -1
  val NotFound_GenericType_MatchByPredicate = -2
  val NotFound_GenericType_MatchByEquals = -3

  def findFirst(as: Array[String], key: String): Int = {
    @annotation.tailrec
    def loop(n: Int): Int = {
      if (n >= as.length) NotFound_SpecifiedType
      else if (as(n) == key) n
      else loop(n + 1)
    }

    loop(0)
  }

  def findFirst[A](a: Array[A], p: A => Boolean): Int = {
    @annotation.tailrec
    def loop(n: Int): Int = {
      if (n >= a.length) NotFound_GenericType_MatchByPredicate
      else if (p(a(n))) n
      else loop(n + 1)
    }

    loop(0)
  }


  def findFirst[A](a: Array[A], key: A): Int = {
    @annotation.tailrec
    def loop(n: Int): Int = {
      if (n >= a.length) NotFound_GenericType_MatchByEquals
      else if (a(n) == key) n
      else loop(n + 1)
    }

    loop(0)
  }

  def run(): Unit = {
    val stringArray = Array("is", "Scala", "cool")
    val intArray = Array(1, 2, 3, 4)

    println(
      """
        |In this exercise we have 3 functions for searching a value in a string
        | findFirst(as: Array[String], key: String): Int
        | findFirst[A](a: Array[A], p: A => Boolean): Int
        | findFirst[A](a: Array[A], key: A): Int
        |
        | When an item will not be found, the return code is negative of the idx of the method in the above list
        | We can observe that the most specific typed function will be used when possible.
      """.stripMargin)

    println(
      s"""
         |String array: ${stringArray.mkString(", ")}
         |
         | Searching: Scala, Java, Java (with predicate matcher)
         |${findFirst(stringArray, "Scala")}
         |${findFirst(stringArray, "Java")}
         |${findFirst(stringArray, (x: String) => x == "Java")}
         |
         |Integer array: ${intArray.mkString(", ")}
         |
         |Searching: 3 (with predicate matcher), 7 (with predicate matcher), 7 (pure value)
         |${findFirst(intArray, (x: Int) => x == 3)}
         |${findFirst(intArray, (x: Int) => x == 7)}
         |${findFirst(intArray, 7)}
       """.stripMargin)
  }
}
