package _1_Introduction_to_functional_programming._6_Purely_functional_state

import scala.annotation.tailrec

trait RNG {
  def nextInt: (Int, RNG)
}

case class SimpleRNG(seed: Long) extends RNG {

  def nextInt: (Int, RNG) = {
    val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL
    val nextRNG = SimpleRNG(newSeed)
    val n = (newSeed >>> 16).toInt
    (n, nextRNG)
  }

}

object RNG {

  def nonNegativeInt(rng: RNG): (Int, RNG) = {
    val (x, rngNext) = rng.nextInt
    if (x == Int.MinValue) nonNegativeInt(rngNext)
    else (-x, rngNext)
  }

  def doubleNaive(rng: RNG): (Double, RNG) = {
    val (x, nextRNG) = RNG.nonNegativeInt(rng)
    (x.toDouble / (Int.MaxValue - 1), nextRNG)
  }

  def intDouble(rng: RNG): ((Int, Double), RNG) = {
    val (int, nextRNG) = RNG.nonNegativeInt(rng)
    val (double, lastRNG) = RNG.doubleNaive(nextRNG)

    ((int, double), lastRNG)
  }

  def doubleInt(rng: RNG): ((Double, Int), RNG) = {
    val (int, nextRNG) = RNG.nonNegativeInt(rng)
    val (double, lastRNG) = RNG.doubleNaive(nextRNG)

    ((double, int), lastRNG)
  }

  def double3(rng: RNG): ((Double, Double, Double), RNG) = {
    val (d1, nextRng1) = RNG.doubleNaive(rng)
    val (d2, nextRng2) = RNG.doubleNaive(nextRng1)
    val (d3, _) = RNG.doubleNaive(nextRng2)

    ((d1, d2, d3), nextRng2)
  }

  def ints(count: Int)(rng: RNG): (List[Int], RNG) = {

    @tailrec
    def loop(xs: List[Int], currentRNG: RNG): (List[Int], RNG) = {
      if (xs.size == count) (xs, currentRNG)
      else {
        val (x, nextRNG) = currentRNG.nextInt
        loop(x :: xs, nextRNG)
      }
    }

    loop(List(), rng)
  }

  type Rand[+A] = RNG => (A, RNG)

  val int: Rand[Int] = _.nextInt

  def unit[A](a: A): Rand[A] = {
    rng => (a, rng)
  }

  def map[A,B](s: Rand[A])(f: A => B): Rand[B] = {
    rng => {
      val (a, rng2) = s(rng)
      (f(a), rng2)
    }
  }

  def double(): Rand[Double] = {
    RNG.map(nonNegativeInt)(_.toDouble / (Int.MaxValue - 1))
  }

}


