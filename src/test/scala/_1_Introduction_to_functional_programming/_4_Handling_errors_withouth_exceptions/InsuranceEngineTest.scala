package _1_Introduction_to_functional_programming._4_Handling_errors_withouth_exceptions

import org.scalatest.matchers.should.Matchers
import org.scalatest.freespec.AnyFreeSpec

class InsuranceEngineTest extends AnyFreeSpec with Matchers {

  "Valid input returns expected value" in {
    InsuranceEngine().parseInsuranceRateQuote("26", "2") shouldBe Some(28.0)
  }

  "Invalid nb of tickers returns None" in {
    InsuranceEngine().parseInsuranceRateQuote("26", "two") shouldBe None
  }

  "Invalid age of tickers returns None" in {
    InsuranceEngine().parseInsuranceRateQuote("twenty", "2") shouldBe None
  }

}
