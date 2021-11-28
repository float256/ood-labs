package gumballmachine.state

import gumballmachine.GumballMachineStateSelector
import java.io.PrintStream

class SoldOutState(
    var machine: GumballMachineStateSelector,
    private var out: PrintStream,
) : GumballMachineState {
    override fun insertQuarter() {
        out.println("You can't insert a quarter, the machine is sold out")
    }

    override fun ejectQuarter() {
        if (machine.getQuarterCount() > 0u) {
            out.println("Returned ${machine.getQuarterCount()} quarters")
            machine.ejectAllQuarters()
        } else {
            out.println("You can't eject, you haven't inserted a quarter yet")
        }
    }

    override fun turnCrank() {
        out.println("You turned but there's no gumballs")
    }

    override fun dispense() {
        out.println("No gumball dispensed")
    }

    override fun fillMachine(numberOfGumballs: UInt) {
        machine.addBalls(numberOfGumballs)
        out.println("You added $numberOfGumballs balls, now there are ${machine.getBallCount()} of them.")

        if (machine.getQuarterCount() > 0u) {
            machine.setHasQuarterState()
        } else {
            machine.setNoQuarterState()
        }
    }

    override fun toString(): String {
        return "sold out"
    }
}