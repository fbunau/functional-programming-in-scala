package _1_Introduction_to_functional_programming._4_Handling_errors_withouth_exceptions

import org.scalatest.matchers.should.Matchers
import org.scalatest.freespec.AnyFreeSpec

import scala.annotation.tailrec

case class Employee(name: String, department: String)

case class Company() {

  val employees = List(Employee("Joe", "Engineering"), Employee("Sue", "Accounting"))

  def lookupByName(name: String): Option[Employee] = {

    @tailrec
    def loop(l : List[Employee]): Option[Employee] = l match {
      case Nil => None
      case x if x.head.name == name => Some(x.head)
      case _ => loop(l.tail)
    }

    loop(employees)
  }

  def manager(employee: Employee): Option[Employee] = employee match {
    case x if x == employees.head => None
    case _ => Some(employees.head)
  }

}

class OptionTest extends AnyFreeSpec with Matchers {

  private val company = Company()

  "Testing framework works" - {

    "Joe is found" in {
      company.lookupByName("Joe") shouldBe Some(Employee("Joe", "Engineering"))
    }

    "Sue is found" in {
      company.lookupByName("Sue") shouldBe Some(Employee("Sue", "Accounting"))
    }

    "Jake is not found" in {
      company.lookupByName("Jake") shouldBe None
    }

  }

  "Test uses our defined optional" in {
    company.lookupByName("Joe").getClass.getCanonicalName shouldBe "_1_Introduction_to_functional_programming._4_Handling_errors_withouth_exceptions.Some"
  }

  "Map transforms the value of an Option if it has a value" in {
    Company().lookupByName("Joe").map(_.department) shouldBe Some("Engineering")
  }

  "Map to department is None for someone that is not an employee" in {
    Company().lookupByName("Jake").map(_.department) shouldBe None
  }

  "FlatMap transforms to None since Joe does not have a manager" in {
    company.lookupByName("Joe").flatMap(company.manager) shouldBe None
  }

  "FlatMap transforms to manager of Sue since she has a manager" in {
    company.lookupByName("Sue").flatMap(company.manager) shouldBe Some(Employee("Joe", "Engineering"))
  }

  "FlatMap transforms to None since Jake is not an employee" in {
    company.lookupByName("Jake").flatMap(company.manager) shouldBe None
  }

  "If there is no value in the option, return the provided default value" in {
    company.lookupByName("Jake").getOrElse("No employee") shouldBe "No employee"
  }

  "If there is value in the option, return the value" in {
    company.lookupByName("Joe").getOrElse("No employee") shouldBe Employee("Joe", "Engineering")
  }

  "If there is no value in the option, return the provided default option" in {
    company.lookupByName("Jake").orElse(Some("No employee")) shouldBe Some("No employee")
  }

  "If there is value in the option, return the option" in {
    company.lookupByName("Joe").orElse(Some("No employee")) shouldBe Some(Employee("Joe", "Engineering"))
  }

  "If the filter does not match the value in the option, return None" in {
    company.lookupByName("Joe").filter(e => e.name.startsWith("S")) shouldBe None
  }

  "If the filter matches the value in the option, return the option" in {
    company.lookupByName("Sue").filter(e => e.name.startsWith("S")) shouldBe Some(Employee("Sue", "Accounting"))
  }

}
