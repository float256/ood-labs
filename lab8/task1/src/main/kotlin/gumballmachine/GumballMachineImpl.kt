package gumballmachine

import gumballmachine.state.*
import java.io.PrintStream

class GumballMachineImpl(
    private var numberOfGumballs: UInt,
    private var out: PrintStream
): GumballMachine, GumballMachineStateSelector {
    private var hasQuarterState = HasQuarterState(this, out)
    private var noQuarterState = NoQuarterState(this, out)
    private var soldState = SoldState(this, out)
    private var soldOutState = SoldOutState(this, out)

    private var currState: GumballMachineState

    init {
        currState = when(numberOfGumballs) {
            0u -> soldOutState
            else -> noQuarterState
        }
    }

    override fun insertQuarter() {
        currState.insertQuarter()
    }

    override fun ejectQuarter() {
        currState.ejectQuarter()
    }

    override fun turnCrank() {
        currState.turnCrank()
        currState.dispense()
    }

    override fun releaseBall() {
        out.println("A gumball comes rolling out the slot...")
        if (numberOfGumballs != 0u) {
            numberOfGumballs -= 1u
        }
    }

    override fun getBallCount(): UInt {
        return numberOfGumballs
    }

    override fun setSoldOutState() {
        currState = soldOutState
    }

    override fun setNoQuarterState() {
        currState = noQuarterState
    }

    override fun setSoldState() {
        currState = soldState
    }

    override fun setHasQuarterState() {
        currState = hasQuarterState
    }

    override fun getState(): GumballMachineState {
        return currState
    }

    override fun toString(): String {
        return """
            Mighty Gumball, Inc.
            Kotlin-enabled Standing Gumball Model #2021 (with state)
            Inventory: $numberOfGumballs gumball${if (numberOfGumballs != 1u) "s" else ""}
            Machine is $currState
        """.trimIndent()
    }
}