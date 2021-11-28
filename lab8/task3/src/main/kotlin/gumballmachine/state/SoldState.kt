package gumballmachine.state

import gumballmachine.GumballMachineStateSelector
import java.io.PrintStream

class SoldState(
    var machine: GumballMachineStateSelector,
    private var out: PrintStream,
) : GumballMachineState {
    override fun insertQuarter() {
        out.println("Please wait, we're already giving you a gumball")
    }

    override fun ejectQuarter() {
        out.println("Sorry you already turned the crank")
    }

    override fun turnCrank() {
        out.println("Turning twice doesn't get you another gumball")
    }

    override fun dispense() {
        machine.releaseBall()
        if (machine.getBallCount() == 0u) {
            out.println("Oops, out of gumballs")
            machine.setSoldOutState()
        } else {
            if (machine.getQuarterCount() > 0u) {
                machine.setHasQuarterState()
            } else {
                machine.setNoQuarterState()
            }
        }
    }

    override fun fillMachine(numberOfGumballs: UInt) {
        out.println("Filling the machine during the ball provisioning process isn't possible")
    }

    override fun toString(): String {
        return "dispensing a gumball"
    }
}