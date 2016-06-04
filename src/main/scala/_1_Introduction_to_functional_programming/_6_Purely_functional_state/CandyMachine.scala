package _1_Introduction_to_functional_programming._6_Purely_functional_state

sealed trait Input

case object Coin extends Input
case object Turn extends Input

case class Machine(locked: Boolean, candies: Int, coins: Int)

object CandyMachine {

  def update = (i: Input) => (s: Machine) => {
    (i, s) match {
      case (_, Machine(_, 0, _)) => s
      case (Coin, Machine(false, _, _)) => s
      case (Turn, Machine(true, _, _)) => s
      case (Coin, Machine(true, candy, coin)) =>
        Machine(false, candy, coin + 1)
      case (Turn, Machine(false, candy, coin)) =>
        Machine(true, candy - 1, coin)
    }
  }

  def f(i: Input): State[Machine, Unit] = {
    State.modify(update(i))
  }

  // More verbose for understanding purposes
  def simulateMachine(inputs: List[Input]): State[Machine, (Int, Int)] = {
    val machines: List[State[Machine, Unit]] =
      inputs.map(i => f(i))

    val sequenced: State[Machine, List[Unit]] =
      State.sequence(machines)

    sequenced.flatMap(x => State.get.map(s => (s.coins, s.candies)))
  }

  def simulateMachineLessVerbose(inputs: List[Input]): State[Machine, (Int, Int)] =  for {
    _ <- State.sequence(inputs map (State.modify[Machine] _ compose update))
    s <- State.get
  } yield (s.coins, s.candies)

}
