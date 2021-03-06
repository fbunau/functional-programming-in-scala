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
    else (Math.abs(x), rngNext)
  }

  def doubleNaive(rng: RNG): (Double, RNG) = {
    val (x, nextRNG) = RNG.nonNegativeInt(rng)
    (x / (Int.MaxValue.toDouble + 1), nextRNG)
  }

  def intDoubleNaive(rng: RNG): ((Int, Double), RNG) = {
    val (int, nextRNG) = RNG.nonNegativeInt(rng)
    val (double, lastRNG) = RNG.doubleNaive(nextRNG)

    ((int, double), lastRNG)
  }

  def doubleIntNaive(rng: RNG): ((Double, Int), RNG) = {
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

  def intsNaive(count: Int)(rng: RNG): (List[Int], RNG) = {

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

  def double: Rand[Double] = {
    RNG.map(nonNegativeInt)(_ / (Int.MaxValue.toDouble + 1))
  }

  def map2[A,B,C](ra: Rand[A], rb: Rand[B])(f: (A, B) => C): Rand[C] = {
    rng => {
      val (a, rng2) = ra(rng)
      val (b, rng3) = rb(rng2)
      (f(a, b), rng3)
    }
  }

  def randPair[A,B](ra: Rand[A], rb: Rand[B]): Rand[(A,B)] = {
    map2(ra, rb)((_, _))
  }

  def intDouble: Rand[(Int, Double)] = {
    randPair(int, double)
  }

  val doubleInt: Rand[(Double, Int)] = {
    randPair(double, int)
  }

  def sequence[A](fs: List[Rand[A]]): Rand[List[A]] = {
    fs.foldRight(unit(List[A]()))( (rng, acc) => map2(rng, acc)(_ :: _) )
  }

  def ints(count: Int): Rand[List[Int]] = {
    sequence(List.fill(count)(int))
  }

  def flatMap[A,B](f: Rand[A])(g: A => Rand[B]): Rand[B] = {
    rng => {
      val (a, rng2) = f(rng)
      g(a)(rng2)
    }
  }

  def nonNegativeLessThan(n: Int): Rand[Int] = {
    flatMap(nonNegativeInt)( x => {
      val mod = x % n
      if (x + (n - 1) - mod >= 0) unit(mod)
      else nonNegativeLessThan(n)
    })
  }

  def _map[A,B](s: Rand[A])(f: A => B): Rand[B] = {
    flatMap(s)(a => unit(f(a)))
  }

  def _map2[A,B,C](ra: Rand[A], rb: Rand[B])(f: (A, B) => C): Rand[C] = {
    flatMap(ra)(a => _map(rb)(b => f(a, b)))
  }

  def randPairViaFlatMaps[A,B](ra: Rand[A], rb: Rand[B]): Rand[(A,B)] = {
    _map2(ra, rb)((_, _))
  }

}


