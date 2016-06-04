package _1_Introduction_to_functional_programming._6_Purely_functional_state

import _1_Introduction_to_functional_programming._6_Purely_functional_state.CandyMachine.simulateMachine
import org.scalatest.{FreeSpec, Matchers}

class CandyMachineTest extends FreeSpec with Matchers {

  "Inserting a coin into a locked machine will cause it to unlock if there’s any candy left" in {
    simulateMachine(List(Coin))
      .run(Machine(locked=true, 1, 0))._2 shouldBe Machine(locked=false, 1, 1)
  }

  "Inserting coins in an unlocked machine has the same result as a single coin" in {
    simulateMachine(List(Coin, Coin))
      .run(Machine(locked=true, 1, 0))._2 shouldBe Machine(locked=false, 1, 1)
  }

  "Turning the knob on an unlocked machine will cause it to dispense candy and become locked" in {
    simulateMachine(List(Coin, Turn))
      .run(Machine(locked=true, 1, 0))._2 shouldBe Machine(locked=true, 0, 1)
  }

  "Turning the knob on a locked machine does nothing" in {
    simulateMachine(List(Turn))
      .run(Machine(locked=true, 1, 0))._2 shouldBe Machine(locked=true, 1, 0)
  }

  "Inserting a coin into an unlocked machine does nothing" in {
    simulateMachine(List(Coin))
      .run(Machine(locked=false, 1, 0))._2 shouldBe Machine(locked=false, 1, 0)
  }

  "A machine that’s out of candy ignores all inputs" - {
    "Machine is locked, and we turn" in {
      simulateMachine(List(Turn))
        .run(Machine(locked=true, 0, 42))._2 shouldBe Machine(locked=true, 0, 42)
    }

    "Machine is unlocked and we turn" in {
      simulateMachine(List(Turn))
        .run(Machine(locked=false, 0, 42))._2 shouldBe Machine(locked=false, 0, 42)
    }

    "Machine is locked and we insert coin" in {
      simulateMachine(List(Coin))
        .run(Machine(locked=true, 0, 42))._2 shouldBe Machine(locked=true, 0, 42)
    }

    "Machine is unlocked and we insert coin" in {
      simulateMachine(List(Coin))
        .run(Machine(locked=false, 0, 42))._2 shouldBe Machine(locked=false, 0, 42)
    }

  }

}
