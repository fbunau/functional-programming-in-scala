package _1_Introduction_to_functional_programming._2_Getting_started_with_functional_programming_in_Scala

class ComposeFunctions {

  def compose[A, B, C](f: A => B, g: B => C): A => C = {
    a => g(f(a))
  }

  val double = (x: Int) => x * 2
  val addFive = (x: Int) => x + 5

  def run(): Unit = {
    val x = 2

    val doubleAndAddFive = compose(double, addFive)

    val doubleAndAddFiveWithScalaLib = double.andThen(addFive)

    println(
      s"""
         |Composing * 2 and add +5 on x = $x"
         |
         |doubleAndAddFive(x)                : ${doubleAndAddFive(x)}
         |doubleAndAddFiveWithScalaLib(x)    : ${doubleAndAddFiveWithScalaLib(x)}
      """.stripMargin)
  }
}
