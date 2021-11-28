package gumballmachine.state

import gumballmachine.GumballMachineStateSelector
import java.io.PrintStream

class SoldOutState(
    var machine: GumballMachineStateSelector,
    var out: PrintStream,
) : GumballMachineState {
    override fun insertQuarter() {
        out.println("You can't insert a quarter, the machine is sold out")
    }

    override fun ejectQuarter() {
        out.println("You can't eject, you haven't inserted a quarter yet")
    }

    override fun turnCrank() {
        out.println("You turned but there's no gumballs")
    }

    override fun dispense() {
        out.println("No gumball dispensed")
    }

    override fun toString(): String {
        return "sold out"
    }
}