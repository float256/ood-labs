package gumballmachine

import gumballmachine.state.*
import java.io.PrintStream

class GumballMachineImpl(
    private var numberOfGumballs: UInt,
    private var out: PrintStream
) : GumballMachine, GumballMachineStateSelector {
    private var hasQuarterState = HasQuarterState(this, out)
    private var noQuarterState = NoQuarterState(this, out)
    private var soldState = SoldState(this, out)
    private var soldOutState = SoldOutState(this, out)

    private var currState: GumballMachineState
    private var numberOfQuarters: UInt = 0u

    init {
        currState = when (numberOfGumballs) {
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
        if ((numberOfGumballs > 0u) && (numberOfQuarters > 0u)) {
            out.println("A gumball comes rolling out the slot...")
            numberOfGumballs--
            numberOfQuarters--
        }
    }

    override fun getBallCount(): UInt {
        return numberOfGumballs
    }

    override fun addBalls(numberOfGumballs: UInt) {
        this.numberOfGumballs += numberOfGumballs
    }

    override fun fillMachine(numberOfGumballs: UInt) {
        currState.fillMachine(numberOfGumballs)
    }

    override fun getQuarterCount(): UInt {
        return numberOfQuarters
    }

    override fun addQuarter() {
        numberOfQuarters++
    }

    override fun ejectAllQuarters() {
        numberOfQuarters = 0u
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
            Number of quarters: $numberOfQuarters
            Machine is $currState
        """.trimIndent()
    }
}