package _2_Functional_design_and_combinator_libraries._7_Purely_functional_parallelism

import java.util.concurrent.{Future, TimeUnit}

case class Map2Future[A, B, C](af: Future[A], bf: Future[B], f: (A, B) => C) extends Future[C] {
  override def isCancelled: Boolean = af.isCancelled && bf.isCancelled
  override def get(): C = f(af.get, bf.get)

  override def get(timeout: Long, unit: TimeUnit): C = {
    val allotedTime = TimeUnit.NANOSECONDS.convert(timeout, unit)

    val start = System.nanoTime
    val a = af.get(allotedTime, TimeUnit.NANOSECONDS)
    val stop = System.nanoTime

    val elapsedATime = stop - start
    val remainingBTime = allotedTime - elapsedATime

    val b = bf.get(remainingBTime, TimeUnit.NANOSECONDS)

    f(a, b)
  }

  override def cancel(mayInterruptIfRunning: Boolean): Boolean = {
    af.cancel(mayInterruptIfRunning) || bf.cancel(mayInterruptIfRunning)
  }

  override def isDone: Boolean = af.isDone && bf.isDone
}