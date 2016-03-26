package _1_Introduction_to_functional_programming._1_What_is_functional_programming

import scala.collection.immutable.Iterable

case class Coffee(price: Double)
class CreditCard() {

  private val min = 1000
  private val max = 9999
  val id = (min + scala.util.Random.nextInt(max - min + 1)).toString

  override def toString: String = s"cc-$id"
}

case class Charge(cc: CreditCard, amount: Double) extends Comparable[Charge] {

  def combine(other: Charge): Charge =
    if (cc == other.cc)
      Charge(cc, amount + other.amount)
    else
      throw new Exception("Can't combine charges of different cards")

  def compareTo(other: Charge) = (amount - other.amount).toInt

}

case class CoalescingPaymentProcessor() {

  def processCharges(charges: Iterable[Charge]): Unit = {
    val groupedCharges = charges.groupBy(_.cc).values.map(_.reduce(_ combine _)).toList.sorted

    println(groupedCharges)
  }

}

class Cafe {

  val CoffeePrice = 5

  def buyCoffee(cc: CreditCard): (Coffee, Charge) = {
    val cup = new Coffee(CoffeePrice)
    (cup, Charge(cc, cup.price))
  }

  def buyCoffees(cc: CreditCard, n: Int): (List[Coffee], Charge) = {
    val purchases: List[(Coffee, Charge)] = List.fill(n)(buyCoffee(cc))
    val (coffees, charges) = purchases.unzip
    (coffees, charges.reduce((c1, c2) => c1.combine(c2)))
  }

}

class CoffeeShop {

  def run(): Unit = {

    val cafe = new Cafe()
    val paymentProcessor = CoalescingPaymentProcessor()

    def buyCoffee(label: String, buyingFunction: Cafe => Iterable[Charge]) = {
      println(s"Buying coffee with $label: ")
      paymentProcessor.processCharges(
        buyingFunction(cafe)
      )
      println
    }

    buyCoffee("sequence comprehension", buyCoffeesWithSequenceComprehension)
    buyCoffee("list mapping", buyCoffeesWithIntegerListMapping)
    buyCoffee("recursive list accumulation using tail recursion", buyCoffeesWithRecursiveListAccumulationUsingTailRecursion)

  }

  def buyCoffeesWithSequenceComprehension(cafe: Cafe) = {
    val charges = for (i <- 1 to 10) yield {
      cafe.buyCoffees(new CreditCard(), i)._2
    }
    charges
  }

  def buyCoffeesWithIntegerListMapping(cafe: Cafe) = {
    val charges =
      (1 to 10).map(i => cafe.buyCoffees(new CreditCard(), i))
               .map(x => x._2)
               .toList
    charges
  }

  def buyCoffeesWithRecursiveListAccumulationUsingTailRecursion(cafe: Cafe) = {

    @annotation.tailrec
    def accumulateChargesFromCoffees(orders: List[Int], accumulatedCharges: List[Charge] = List()): List[Charge] = {
      if (orders.isEmpty)
        return accumulatedCharges

      val (_, charge) = cafe.buyCoffees(new CreditCard(), orders.head)
      accumulateChargesFromCoffees(orders.tail, charge :: accumulatedCharges)
    }

    accumulateChargesFromCoffees(List.range(1, 11))
  }

}
