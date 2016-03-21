package _1_Introduction_to_functional_programming._3_Functional_data_structures

class HelpingTypeInferenceWithOptionalParameterList {

  def dropWhile[A](l: List[A], f: A => Boolean): List[A] = l match {
    case Cons(h,t) if f(h) => dropWhile(t, f)
    case _ => l
  }

  def dropWhileEnhanced[A](l: List[A])(f: A => Boolean): List[A] = l match {
    case Cons(h,t) if f(h) => dropWhile(t, f)
    case _ => l
  }

  def run(): Unit = {
    println("Type inference in Scala is not complete, and sometimes must be helped")

    val xs: List[Int] = MyList(1,2,3,4,5)
    //val result = List.dropWhile(xs, x => x < 4) // Does not compile
    val result1 = dropWhile(xs, (x: Int) => x < 4)
    val result2 = dropWhileEnhanced(xs)(x => x < 4)

    println(result1)
    println(result2)
  }

}
