package _1_Introduction_to_functional_programming._2_Getting_started_with_functional_programming_in_Scala

class CurryVsPartial {

  def compute(a: Int, b: Int, c: Int, d: Int): Int = {
    a + b - c + d
  }

  def runCurry(): Unit = {
    println("Curry")

    val curryCompute = (compute _).curried
    val curryCompute_2 = curryCompute(2)
    val curryCompute_2_3_4 = curryCompute_2(3)(4)

    println(
      s"""
         |curryCompute(2)       : $curryCompute_2
         |curryCompute_2(3)(4)  : $curryCompute_2_3_4
         |curryCompute_2(3, 4)  : invalid
         |curryCompute_2_3_4(5) : ${curryCompute_2_3_4(5)}
      """.stripMargin)
  }

  def runPartial(): Unit = {
    println("Partial")

    val computePartial = compute(2, _: Int, 4, _: Int)

    println(
      s"""
         |computePartial is (2, ?, 4, ?)
         |computePartial        : $computePartial
         |computePartial(3)     : invalid
         |computePartial(3, _)  : ${computePartial(3, _:Int)}
         |computePartial(3, 5)  : ${computePartial(3, 5)}
      """.stripMargin)
  }

  def run(): Unit = {
    println("Example of Curry vs. Partial (a+b-c+d)")

    runCurry()
    runPartial()
  }
}
