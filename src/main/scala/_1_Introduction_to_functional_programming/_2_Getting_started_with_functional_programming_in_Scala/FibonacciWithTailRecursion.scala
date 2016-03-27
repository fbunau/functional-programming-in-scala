package _1_Introduction_to_functional_programming._2_Getting_started_with_functional_programming_in_Scala

case class FibonacciWithTailRecursion() {

  def fib(n: Int): Int = {

    @annotation.tailrec
    def fib(n: Int, prev: Int, curr: Int): Int =
      if (n == 0) prev
      else fib(n - 1, curr, prev + curr)

    fib(n, 0, 1)
  }

}
