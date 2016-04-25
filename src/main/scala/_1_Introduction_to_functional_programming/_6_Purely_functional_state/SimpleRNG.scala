package _1_Introduction_to_functional_programming._6_Purely_functional_state

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

}


