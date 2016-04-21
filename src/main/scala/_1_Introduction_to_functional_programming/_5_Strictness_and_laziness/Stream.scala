package _1_Introduction_to_functional_programming._5_Strictness_and_laziness

import scala.annotation.tailrec

sealed trait Stream[+A] {

  def toList: List[A] = {
    @tailrec
    def loop(s: Stream[A], acc : List[A]): List[A] = s match {
      case Cons(h, t) => loop(t(), h() :: acc)
      case _ => acc
    }

    loop(this, List()).reverse
  }

    def take(n : Int): Stream[A] = this match {
      case Cons(h, t) if n > 1 => Stream.cons(h(), t().take(n-1))
      case Cons(h, _) if n == 1 => Stream.cons(h(), Empty)
      case _ => Empty
    }

    def drop(n : Int): Stream[A] = this match {
      case Cons(_, t) if n > 0 => t().drop(n-1)
      case _ => this
    }

    def takeWhile(p: A => Boolean): Stream[A] = this match {
      case Cons(h, t) if p(h()) => Stream.cons(h(), t().takeWhile(p))
      case _ => Empty
    }

    def foldRight[B](z: => B)(f: (A, => B) => B): B =
      this match {
        case Cons(h,t) => f(h(), t().foldRight(z)(f))
        case _ => z
      }

    def exists(p: A => Boolean): Boolean =
      foldRight(false)((a, b) => p(a) || b)

}

case object Empty extends Stream[Nothing]
case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A]

object Stream {

  def cons[A](hd: => A, tl: => Stream[A]): Stream[A] = {
    lazy val head = hd
    lazy val tail = tl
    Cons(() => head, () => tail)
  }

  def empty[A]: Stream[A] = Empty

  // using a variadic apply, will evaluate the elements
  def apply[A](as: A*): Stream[A] =
    if (as.isEmpty)
      empty
    else
      cons(as.head, apply(as.tail: _*))


  def constant[A](a: A): Stream[A] = {
    cons(a, constant(a))
  }

}