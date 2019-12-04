package _2_Functional_design_and_combinator_libraries._7_Purely_functional_parallelism

import java.util.concurrent.{Callable, ExecutorService, Future}

import scala.concurrent.duration.TimeUnit

object Par {

  type Par[A] = ExecutorService => Future[A]

  def unit[A](a: A): Par[A] =
    (ExecutorService) => UnitFuture(a)

  def lazyUnit[A](a: => A): Par[A] =
    fork(unit(a))

  def asyncF[A, B](f: A => B): A => Par[B] =
    a => lazyUnit(f(a))

  def run[A](es: ExecutorService)(a: => Par[A]): Future[A] =
    a(es)

  def fork[A](a: => Par[A]): Par[A] = {
    es => es.submit(new Callable[A] {
      override def call(): A = a(es).get
    })
  }

  def map2[A,B,C](a: Par[A], b: Par[B])(f: (A, B) => C): Par[C] = {
    (es: ExecutorService) => {
      val (af, bf) = (a(es), b(es))

      Map2Future(af, bf, f)
    }
  }

  def map[A, B](parList: Par[A])(f: A => B): Par[B] =
    map2(parList, unit(()))((a, _) => f(a))

  def parMap[A, B](ps: List[A])(f: A => B): Par[List[B]] =
    fork(sequence(ps.map(asyncF(f))))

  def sequence[A](as: List[Par[A]]): Par[List[A]] = {
    val z = unit(List[A]())
    as.foldRight(z)((a, r) => map2(a, r)((h, t) => h :: t))
  }

  def sequenceBalanced[A](as: IndexedSeq[Par[A]]): Par[IndexedSeq[A]] = as.size match {
    case 0 => unit(Vector())
    case 1 => map(as.head)(a => Vector(a))
    case n =>
      val (l, r) = as.splitAt(n / 2)
      map2(sequenceBalanced(l), sequenceBalanced(r))(_ ++ _)
  }

  // These are very similar to the implementations from List.scala ! (compare and contrast)

  def foldRight[A, B](as: Par[List[A]], z: Par[B])(f: (A, B) => B): Par[B] = {
    map2(as, z)(
      (la: List[A], bz: B) =>
        la.foldRight(bz)(
          (a, b) => f(a, b)
        )
    )
  }

  def concat[A](lists: Par[List[List[A]]]): Par[List[A]] = {
    foldRight(lists, unit(List[A]()))(
      (a: List[A], b: List[A]) => a ++ b
    )
  }

  def flatMap[A, B](as: Par[List[A]])(f: A => List[B]): Par[List[B]] = {
    concat(map(as)(_.map(f)))
  }

  def filter[A](as: Par[List[A]])(f: A => Boolean): Par[List[A]] = {
    flatMap(as)(a => if (f(a)) List(a) else List())
  }

  def parFilter[A](as: List[A])(f: A => Boolean): Par[List[A]] = {
    val parAs: Par[List[A]] = fork(sequence(as.map(unit)))
    filter(parAs)(f)
  }

  //

  def sumNaive(ints: IndexedSeq[Int]): Par[Int] = {
    if (ints.size <= 1)
      unit(ints.headOption.getOrElse(0))
    else {
      val (l, r) = ints.splitAt(ints.size / 2)
      map2(fork(sumNaive(l)), fork(sumNaive(r)))(_ + _)
    }
  }

  //

  def sum(ints: IndexedSeq[Int]): Par[Int] = {
    val intsPar: IndexedSeq[Par[Int]] = ints.map(asyncF(a => a))
    val parSequence: Par[IndexedSeq[Int]] = sequenceBalanced(intsPar)
    val parList: Par[List[Int]] = map(parSequence)(_.toList)
    foldRight(parList, unit(0))(_ + _)
  }

  def max(ints: IndexedSeq[Int]): Par[Int] = {
    val intsPar: IndexedSeq[Par[Int]] = ints.map(asyncF(a => a))
    val parSequence: Par[IndexedSeq[Int]] = sequenceBalanced(intsPar)
    val parList: Par[List[Int]] = map(parSequence)(_.toList)
    foldRight(parList, unit(Int.MinValue))(Math.max)
  }

  def sortPar(parList: Par[List[Int]]): Par[List[Int]] = {
    map(parList)(_.sorted)
  }

}

private case class UnitFuture[A](a : A) extends Future[A] {
  override def get: A = a

  override def isCancelled: Boolean = false
  override def get(timeout: Long, unit: TimeUnit): A = a
  override def cancel(evenIfRunning: Boolean): Boolean = false
  override def isDone: Boolean = true
}
