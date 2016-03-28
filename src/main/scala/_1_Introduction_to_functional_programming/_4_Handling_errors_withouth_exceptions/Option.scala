package _1_Introduction_to_functional_programming._4_Handling_errors_withouth_exceptions

sealed trait Option[+A] {

  // transform the value
  def map[B](f: A => B): Option[B] = this match {
    case None => None
    case Some(x) => Some(f(x))
  }

  // transform the value using a function that can fail
  def flatMap[B](f: A => Option[B]): Option[B] = this match {
    case None => None
    case Some(x) => f(x)
  }

  // convert an option to a value, and provide a default value in case it's None
  def getOrElse[B >: A](default: => B): B = this match {
    case None => default
    case Some(x) => x
  }

  // similar to getOrElse, we provide an alternative Option to return if the first is undefined
  def orElse[B >: A](ob: => Option[B]): Option[B] = this match {
    case None => ob
    case x => x
  }

  // used to convert successes into failures if they don't match a given predicate
  def filter(f: A => Boolean): Option[A] = this match {
    case Some(x) if f(x) => this
    case _ => None
  }

}

case class Some[+A](get: A) extends Option[A]
case object None extends Option[Nothing]

object Option {

//  def map2[A,B,C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] = {
//    a.flatMap(aa => b map (bb => f(aa, bb)))
//  }

  def map2[A,B,C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] = {
    for {
      aa <- a
      bb <- b
    } yield f(aa, bb)
  }

}