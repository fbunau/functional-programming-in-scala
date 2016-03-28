package _1_Introduction_to_functional_programming._4_Handling_errors_withouth_exceptions

trait Either {

  sealed trait Either[+E, +A]
  case class Left[+E](value: E) extends Either[E, Nothing]
  case class Right[+A](value: A) extends Either[Nothing, A]

}
