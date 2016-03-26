package _1_Introduction_to_functional_programming._2_Getting_started_with_functional_programming_in_Scala

class CurryFunctions {

  val A = 4
  val B = 5

  def sum(a: Int, b: Int) = {
    a + b
  }

  def curry[A,B,C](f: (A, B) => C): A => (B => C) = {
    a => b => f(a, b)
  }

  def sumMore(a: Int)(b: Int)(c: Int)(d: Int): Int = {
    a + b + c + d
  }

  def runWithTheoryImplementation(): Unit = {
    println("Curry functions with Theory implementation (A+B)")

    val curriedSum = curry(sum)
    val curriedSum_A = curriedSum(A)

    println(
      s"""
         |A: $A; B: $B
         |curriedSum      : $curriedSum
         |curriedSum(A)   : ${curriedSum(A)}
         |curriedSum_A(B) : ${curriedSum_A(B)}
      """.stripMargin)
  }

  def runWithScalaImplementation(): Unit = {
    println("Curry functions with Scala implementation (A+B)")

    val curriedSum = (sum _).curried
    val curriedSum_A = curriedSum(A)

    println(
      s"""
         |A: $A; B: $B
         |curriedSum      : $curriedSum
         |curriedSum(A)   : ${curriedSum(A)}
         |curriedSum_A(B) : ${curriedSum_A(B)}
      """.stripMargin)
  }

  def runWithOptionalParametersList(): Unit = {
    println("Curry using optional parameters list (a+b+c+d)")

    val storedCurrySumMore = sumMore(1)(2) _
    val storedCurrySumMore2 = storedCurrySumMore(3)

    println(
      s"""
         |Numbers to sum : 1, 2, 3, 4
         |sumMore(1)(2) _              : ${sumMore(1)(2) _}
         |sumMore(1)(2)(3) _           : ${sumMore(1)(2)(3) _}
         |storedCurrySumMore           : $storedCurrySumMore
         |storedCurrySumMore(3)        : ${storedCurrySumMore(3)}
         |storedCurrySumMore2          : $storedCurrySumMore2
         |storedCurrySumMore(3)(4)     : ${storedCurrySumMore(3)(4)}
         |storedCurrySumMore2(4)       : ${storedCurrySumMore2(4)}
         |sumMore(1)(2)(3)(4)          : ${sumMore(1)(2)(3)(4)}
      """.stripMargin)

  }

  def run(): Unit = {
    runWithTheoryImplementation()
    runWithScalaImplementation()
    runWithOptionalParametersList()
  }

}
