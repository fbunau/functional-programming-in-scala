package _2_Functional_design_and_combinator_libraries._7_Purely_functional_parallelism

trait Par[A]

object Par {
  def unit[A](a: => A): Par[A] = ???
  def get[A](a: => Par[A]): A = ???
  def map2[A,B,C](a: Par[A], b: Par[B])(f: (A, B) => C): Par[C] = ???
}

object Parallel {
  def sum(ints: IndexedSeq[Int]): Par[Int] =
    if (ints.size <= 1)
      Par.unit(ints.headOption.getOrElse(0))
    else {
      val (l, r) = ints.splitAt(ints.size / 2)
      Par.map2(sum(l), sum(r))(_ + _)
    }
}
