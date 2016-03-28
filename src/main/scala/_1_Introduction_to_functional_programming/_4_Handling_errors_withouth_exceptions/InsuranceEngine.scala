package _1_Introduction_to_functional_programming._4_Handling_errors_withouth_exceptions

case class InsuranceEngine() {

  def insuranceRateQuote(age: Int, numberOfSpeedingTickets: Int): Double = {
    age + numberOfSpeedingTickets
  }

  def parseInsuranceRateQuote(age: String, numberOfSpeedingTickets: String): Option[Double] = {
    val optAge: Option[Int] = Try(age.toInt)
    val optTickets: Option[Int] = Try(numberOfSpeedingTickets.toInt)

    Option.map2(optAge, optTickets)(insuranceRateQuote)
  }

  def Try[A](a: => A): Option[A] =
    try Some(a)
    catch {
      case e: Exception => None
    }
}
