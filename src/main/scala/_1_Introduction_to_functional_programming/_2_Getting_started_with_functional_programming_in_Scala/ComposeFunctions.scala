package _1_Introduction_to_functional_programming._2_Getting_started_with_functional_programming_in_Scala

case class ComposeFunctions() {

  def compose[A, B, C](f: A => B, g: B => C): A => C = {
    a => g(f(a))
  }

  def composeWithScalaLib[A, B, C](f: A => B, g: B => C): A => C = {
    f.andThen(g)
  }

}
