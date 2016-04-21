package _1_Introduction_to_functional_programming._2_Getting_started_with_functional_programming_in_Scala

case class SimplePolymorphicHOF() {

  def isSorted[A](as: List[A], ordered: (A,A) => Boolean): Boolean = {

    @annotation.tailrec
    def loop(i: Int): Boolean =
      if (i >= as.length-1) true
      else if (ordered(as(i), as(i+1))) false
      else loop(i+1)

    loop(0)

  }

}
