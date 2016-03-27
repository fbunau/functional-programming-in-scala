package _1_Introduction_to_functional_programming._2_Getting_started_with_functional_programming_in_Scala

case class PolymorphicFunctions() {

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

}
