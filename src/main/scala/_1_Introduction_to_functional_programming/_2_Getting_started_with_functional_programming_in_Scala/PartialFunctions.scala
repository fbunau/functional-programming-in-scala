package _1_Introduction_to_functional_programming._2_Getting_started_with_functional_programming_in_Scala

class PartialFunctions {

  val A = 4
  val B = 5

  def sum(a: Int, b: Int) = {
    a + b
  }

  def partialLeft[A, B, C](a: A, f: (A, B) => C): B => C = {
    (b: B) => f(a, b)
  }

  def runWithTheoryImplementation(): Unit = {
    println("Partial functions with Theory implementation (A+B)")

    val sumWithA = partialLeft(A, sum)

    println(
      s"""
        |A: $A; B: $B
        |sumWithA   : $sumWithA
        |sumWithA(B): ${sumWithA(B)}
      """.stripMargin)
  }

  def runWithScalaImplementation(): Unit = {
    println("Partial functions with Scala implementation (A+B)")

    val sumWithA = sum(A, _:Int)
    println(
      s"""
         |A: $A; B: $B
         |sumWithA: $sumWithA
         |sumWithB: ${sumWithA(B)}
      """.stripMargin)
  }

  def run(): Unit = {
    runWithTheoryImplementation()
    runWithScalaImplementation()
  }


}
