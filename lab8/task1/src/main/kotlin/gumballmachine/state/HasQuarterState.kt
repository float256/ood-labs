package gumballmachine.state

import gumballmachine.GumballMachineStateSelector
import java.io.PrintStream

class HasQuarterState(
	var machine: GumballMachineStateSelector,
	var out: PrintStream
) : GumballMachineState {
	override fun insertQuarter() {
		out.println("You can't insert another quarter")
	}

	override fun ejectQuarter() {
		out.println("Quarter returned")
		machine.setNoQuarterState()
	}

	override fun turnCrank() {
		out.println("You turned...")
		machine.setSoldState()
	}

	override fun dispense() {
		out.println("No gumball dispensed")
	}

	override fun toString(): String {
		return "waiting for turn of crank"
	}
}