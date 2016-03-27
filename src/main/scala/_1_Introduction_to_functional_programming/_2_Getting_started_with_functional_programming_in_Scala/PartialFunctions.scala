package _1_Introduction_to_functional_programming._2_Getting_started_with_functional_programming_in_Scala

case class PartialFunctions()  {

  def partialLeft[A, B, C](a: A, f: (A, B) => C): B => C = {
    (b: B) => f(a, b)
  }

  def partialWithScalaLib[A, B, C](a: A, f: (A, B) => C): B => C = {
    f(a, _:B)
  }

}
