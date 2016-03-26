package _1_Introduction_to_functional_programming._3_Functional_data_structures

sealed trait List[+A]
case object Nil extends List[Nothing]
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {

  def apply[A](as: A*): List[A] =
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  def sumNaive(ints: List[Int]): Int = ints match {
    case Nil => 0
    case Cons(x, xs) => x + sumNaive(xs)
  }

  def productNaive(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x, xs) => x * productNaive(xs)
  }

  def dropWhile[A](l: List[A], f: A => Boolean): List[A] = l match {
    case Cons(h,t) if f(h) => dropWhile(t, f)
    case _ => l
  }

  def dropWhileEnhanced[A](l: List[A])(f: A => Boolean): List[A] = l match {
    case Cons(h,t) if f(h) => dropWhile(t, f)
    case _ => l
  }

  def foldRight[A,B](as: List[A], z: B)(f: (A, B) => B): B = as match {
    case Nil => z
    case Cons(x, xs) => f(x, foldRight(xs, z)(f))
  }

  def sum(ns: List[Int]) =
    foldRight(ns, 0)((x,y) => x + y)

  def product(ns: List[Double]) =
    foldRight(ns, 1.0)(_ * _)

}

