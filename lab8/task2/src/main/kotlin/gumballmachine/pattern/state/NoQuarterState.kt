package gumballmachine.pattern.state

import gumballmachine.pattern.GumballMachineStateSelector
import java.io.PrintStream

class NoQuarterState(
    var machine: GumballMachineStateSelector,
    private var out: PrintStream
) : GumballMachineState {
    override fun insertQuarter() {
        out.println("You inserted a quarter")
        machine.addQuarter()
        machine.setHasQuarterState()
    }

    override fun ejectQuarter() {
        out.println("You haven't inserted a quarter")
    }

    override fun turnCrank() {
        out.println("You turned but there's no quarter")
    }

    override fun dispense() {
        out.println("You need to pay first")
    }

    override fun toString(): String {
        return "waiting for quarter"
    }
}