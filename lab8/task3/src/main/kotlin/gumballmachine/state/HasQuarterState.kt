package gumballmachine.state

import gumballmachine.GumballMachineConstants.MAX_QUARTERS_COUNT
import gumballmachine.GumballMachineStateSelector
import java.io.PrintStream

class HasQuarterState(
    var machine: GumballMachineStateSelector,
    private var out: PrintStream
) : GumballMachineState {
    override fun insertQuarter() {
        if (machine.getQuarterCount() < MAX_QUARTERS_COUNT) {
            machine.addQuarter()
            out.println("You inserted a quarter. Machine now contains ${machine.getQuarterCount()} quarters")
        } else {
            out.println("You cannot add more than $MAX_QUARTERS_COUNT quarters")
        }
    }

    override fun ejectQuarter() {
        out.println("Returned ${machine.getQuarterCount()} quarters")
        machine.ejectAllQuarters()
        machine.setNoQuarterState()
    }

    override fun turnCrank() {
        out.println("You turned...")
        machine.setSoldState()
    }

    override fun dispense() {
        out.println("No gumball dispensed")
    }

    override fun fillMachine(numberOfGumballs: UInt) {
        machine.addBalls(numberOfGumballs)
        out.println("You added $numberOfGumballs balls, now there are ${machine.getBallCount()} of them.")
    }

    override fun toString(): String {
        return "waiting for turn of crank"
    }
}