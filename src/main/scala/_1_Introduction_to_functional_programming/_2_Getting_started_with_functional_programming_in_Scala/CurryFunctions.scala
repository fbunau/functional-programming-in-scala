package _1_Introduction_to_functional_programming._2_Getting_started_with_functional_programming_in_Scala

case class CurryFunctions() {

  def curry[A,B,C](f: (A, B) => C): A => (B => C) = {
    a => b => f(a, b)
  }

  def curryWithScalaLib[A,B,C](f: (A, B) => C): A => (B => C) = {
    f.curried
  }

  def curryWithOptionalParameterList(a: Int)(b: Int)(c: Int)(d: Int): Int = {
    a + b + c + d
  }

  def uncurry[A,B,C](f: A => B => C): (A, B) => C = {
    f(_)(_)
    // (a: A, b: B) => f(a)(b)
  }

}
