package _1_Introduction_to_functional_programming._3_Functional_data_structures

import scala.annotation.tailrec

sealed trait List[+A]
case object Nil extends List[Nothing]
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {

  def apply[A](as: A*): List[A] =
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  def tail[A](l: List[A]) = l match {
    case Cons(_, t) => t
    case _ => Nil
  }

  def setHead[A](l: List[A], h: A) = l match {
    case Cons(_, t) => Cons(h, t)
    case _ => Cons(h, Nil)
  }

  def sumNaive(ints: List[Int]): Int = ints match {
    case Nil => 0
    case Cons(x, xs) => x + sumNaive(xs)
  }

  def productNaive(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x, xs) => x * productNaive(xs)
  }

  def drop[A](l: List[A], n: Int): List[A] = {
    if (n == 0) l
    else drop(tail(l), n - 1)
  }

  def append[A](l1: List[A], l2: List[A]): List[A] = l1 match {
    case Nil => l2
    case Cons(h, t) => Cons(h, append(t, l2))
  }

  def init[A](l: List[A]): List[A] = l match {
    case Cons(x, Cons(_, Nil)) => Cons(x, Nil)
    case Cons(h, t) => Cons(h, init(t))
    case Nil => Nil
  }

  def dropWhile[A](l: List[A], f: A => Boolean): List[A] = l match {
    case Cons(h, t) if f(h) => dropWhile(t, f)
    case _ => l
  }

  def dropWhileEnhanced[A](l: List[A])(f: A => Boolean): List[A] = l match {
    case Cons(h, t) if f(h) => dropWhile(t, f)
    case _ => l
  }

  ///

  /*
   * f(1, f(2, f(3, f(4, z))))
   * f(a, b) = Cons(a, b)
   * Cons(1, Cons(2, Cons(3, Cons(4, Nil))))
   */
  def foldRight[A, B](as: List[A], z: B)(f: (A, B) => B): B = as match {
    case Nil => z
    case Cons(x, xs) => f(x, foldRight(xs, z)(f))
  }

  def sumRight(ns: List[Int]) =
    foldRight(ns, 0)((x, y) => x + y)

  def productRight(ns: List[Double]) =
    foldRight(ns, 1.0)(_ * _)

  def lengthRight[A](as: List[A]): Int =
    foldRight(as, 0)((_, c) => c + 1)

  def appendWithFoldRight[A](l1: List[A], l2: List[A]): List[A] = {
    foldRight(l1, l2)(Cons(_, _))
  }

  def concat[A](lists: List[List[A]]): List[A] = {
    foldRight(lists, List[A]())(appendWithFoldRight)
  }

  ///

  /*
   * f(f(f(f(z, 1), 2), 3), 4)
   * f(b, a) = Cons(a, b)
   * Cons(4, Cons(3, Cons(2, Cons(1, Nil))))
   */
  @tailrec
  def foldLeft[A, B](as: List[A], z: B)(f: (B, A) => B): B = as match {
    case Nil => z
    case Cons(x, xs) => foldLeft(xs, f(z, x))(f)
  }

  def sumLeft(ns: List[Int]) =
    foldLeft(ns, 0)((x, y) => x + y)

  def productLeft(ns: List[Double]) =
    foldLeft(ns, 1.0)(_ * _)

  def lengthLeft[A](as: List[A]): Int =
    foldLeft(as, 0)((c, _) => c + 1)

  def reverse[A](as: List[A]): List[A] =
    foldLeft(as, List[A]())((rl, a) => Cons(a, rl))

  //

  /**
    * We must go from (foldLeft):
    * f(f(f(f(z, 1), 2), 3), 4)
    *
    * to foldRight
    * f(1, f(2, f(3, f(4, z))))
    *
    * Let's keep the structure for a function 'c'
    * c(c(c(c(z, 1), 2), 3), 4)
    *
    * defining 'c' as :
    * c(g, a) = B => B
    *
    * c(g, a) = z => g(f(a, z))
    *
    * we get :
    * c(c(c(c(g, 1), 2), 3), 4)
    *
    * c(c(c(z => g(f(1, z)), 2), 3), 4)
    * c(c(z => g(f(1, f(2, z))), 3), 4)
    * c(z => g(f(1, f(2, f(3, z)))), 4)
    * z => g(f(1, f(2, f(3, f(4, z)))))
    *
    * defining 'g' as identity: b => b
    *
    * we get foldRight:
    *
    * z => id(f(1, f(2, f(3, f(4, z)))))
    * f(1, f(2, f(3, f(4, z))))
    */
  def foldRightWithFoldLeft[A, B](as: List[A], z: B)(f: (A, B) => B) ={
    type BtoB = B => B
    def id: BtoB = (b: B) => b

    def c: (BtoB, A) => BtoB =
      (g: BtoB, a: A) => (b: B) => g(f(a, b))

    foldLeft(as, id)(c)(z)
  }

  /**
    * We must go from (foldRight):
    * f(1, f(2, f(3, f(4, z))))
    *
    * to foldLeft
    * f(f(f(f(z, 1), 2), 3), 4)
    *
    * Let's keep the structure for a function 'c'
    *
    * c(1, c(2, c(3, c(4, g))))
    * defining 'c' as :
    *
    * c(a, g) = B => B
    * c(a, g) = z => g(f(z, a))
    *
    * we get :
    * c(1, c(2, c(3, c(4, g))))
    * c(1, c(2, c(3, z => g(f(z, 4)))))
    * c(1, c(2, z => g(f(f(z, 3), 4))))
    * c(1, z => g(f(f(f(z, 2), 3), 4)))
    * z => g(f(f(f(f(z, 1) , 2), 3), 4))
    *
    * defining 'g' as identity: b => b
    *
    * we get foldLeft:
    *
    * z => id(f(f(f(f(z, 1), 2), 3), 4))
    * f(f(f(f(z, 1), 2), 3), 4)
    **/
  def foldLeftWithFoldRight[A, B](as: List[A], z: B)(f: (B, A) => B) = {
    type BtoB = B => B
    def id: BtoB = (b: B) => b

    def c: (A, BtoB) => BtoB =
      (a: A, g: BtoB) => (b: B) => g(f(b, a))

    foldRight(as, id)(c)(z)
  }

  //

  def map[A, B](as: List[A])(f: A => B): List[B] = {
    foldRight(as, List[B]())((h, t) => Cons(f(h), t))
  }

  def flatMap[A, B](as: List[A])(f: A => List[B]): List[B] = {
    concat(map(as)(f))
  }

  def filter[A](as: List[A])(f: A => Boolean): List[A] = {
    flatMap(as)(a => if (f(a)) List(a) else List())
  }

  def zipWith[A, B, C](l1: List[A], l2: List[B])(f: (A, B) => C): List[C] = (l1, l2) match {
    case (_, Nil) => Nil
    case (Nil, _) => Nil
    case (Cons(h1, t1), Cons(h2, t2)) => Cons(f(h1, h2), zipWith(t1, t2)(f))
  }

  //

  def naiveAddOne(as: List[Int]): List[Int] = {
    foldRight(as, List[Int]())((h, t) => Cons(h + 1, t))
  }

  def naiveDoubleToString(as: List[Double]): List[String] = {
    foldRight(as, List[String]())((h, t) => Cons(h.toString, t))
  }

  def filterRecursive[A](as: List[A])(f: A => Boolean): List[A] = as match {
    case Nil => Nil
    case Cons(h, t) if f(h) => Cons(h, filterRecursive(t)(f))
    case Cons(h, t) => filterRecursive(t)(f)
  }

  def filterFoldRight[A](as: List[A])(f: A => Boolean): List[A] = {
    foldRight(as, List[A]())(
      (h, t) =>
        if (f(h)) Cons(h, t)
        else t
    )
  }

  def addTwoIntLists(l1: List[Int], l2: List[Int]): List[Int] = (l1, l2) match {
    case (Nil, Nil) => Nil
    case (Cons(h, t), Nil) => Cons(h, addTwoIntLists(t, Nil))
    case (Nil, Cons(h, t)) => Cons(h, addTwoIntLists(t, Nil))
    case (Cons(h1, t1), Cons(h2, t2)) => Cons(h1 + h2, addTwoIntLists(t1, t2))
  }

}


