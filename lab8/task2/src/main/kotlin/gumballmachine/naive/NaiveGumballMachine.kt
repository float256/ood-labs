package gumballmachine.naive

import gumballmachine.pattern.GumballMachineConstants

class NaiveGumballMachine(count: UInt) {
    var state = NaiveGumballMachineState.SOLD_OUT
    private var numberOfGumball = 0u
    private var numberOfQuarters = 0u

    fun insertQuarter() {
        when (state) {
            NaiveGumballMachineState.HAS_QUARTER -> {
                if (numberOfQuarters < GumballMachineConstants.MAX_QUARTERS_COUNT) {
                    numberOfQuarters++
                    println("You inserted a quarter. Machine now contains $numberOfQuarters quarters")
                } else {
                    println("You cannot add more than ${GumballMachineConstants.MAX_QUARTERS_COUNT} quarters")
                }
            }
            NaiveGumballMachineState.NO_QUARTER -> {
                println("You inserted a quarter")
                numberOfQuarters++
                state = NaiveGumballMachineState.HAS_QUARTER
            }
            NaiveGumballMachineState.SOLD_OUT -> {
                println("You can't insert a quarter, the machine is sold out")
            }
            NaiveGumballMachineState.SOLD -> {
                println("Please wait, we're already giving you a gumball")
            }
        }
    }

    fun ejectQuarter() {
        when (state) {
            NaiveGumballMachineState.HAS_QUARTER -> {
                println("Returned $numberOfQuarters quarters")
                numberOfQuarters = 0u
                state = NaiveGumballMachineState.NO_QUARTER
            }
            NaiveGumballMachineState.NO_QUARTER -> {
                println("You haven't inserted a quarter")
            }
            NaiveGumballMachineState.SOLD -> {
                println("Sorry, you already turned the crank")
            }
            NaiveGumballMachineState.SOLD_OUT -> {
                println("You can't eject, you haven't inserted a quarter yet")
            }
        }
    }

    fun turnCrank() {
        when (state) {
            NaiveGumballMachineState.SOLD -> {
                println("Turning twice doesn't get you another gumball!")
            }
            NaiveGumballMachineState.NO_QUARTER -> {
                println("You turned but there's no quarter")
            }
            NaiveGumballMachineState.SOLD_OUT -> {
                println("You turned, but there are no gumballs")
            }
            NaiveGumballMachineState.HAS_QUARTER -> {
                println("You turned...")
                state = NaiveGumballMachineState.SOLD
            }
        }
    }

    fun dispense() {
        when (state) {
            NaiveGumballMachineState.SOLD -> {
                println("A gumball comes rolling out the slot")
                releaseBall()
                state = if (getBallCount() == 0u) {
                    println("Oops, out of gumballs")
                    NaiveGumballMachineState.SOLD_OUT
                } else {
                    if (numberOfQuarters > 0u) {
                        NaiveGumballMachineState.HAS_QUARTER
                    } else {
                        NaiveGumballMachineState.NO_QUARTER
                    }
                }
            }
            NaiveGumballMachineState.NO_QUARTER -> {
                println("You need to pay first")
            }
            NaiveGumballMachineState.SOLD_OUT -> {
                println("No gumball dispensed")
            }
            NaiveGumballMachineState.HAS_QUARTER -> {
                println("No gumball dispensed")
            }
        }
    }

    private fun releaseBall() {
        if ((numberOfGumball > 0u) && (numberOfQuarters > 0u)) {
            println("A gumball comes rolling out the slot...")
            numberOfGumball--
            numberOfQuarters--
        }
    }

    fun refill(numGumBalls: UInt) {
        numberOfGumball = numGumBalls
        state = NaiveGumballMachineState.NO_QUARTER
    }

    override fun toString(): String {
        val result = StringBuffer()
        result.append("\nMighty Gumball, Inc.")
        result.append("\nJava-enabled Standing Gumball Model #2004\n")
        result.append("Inventory: $numberOfGumball gumball")
        if (numberOfGumball != 1u) {
            result.append("s")
        }
        result.append("\nMachine is ")
        when (state) {
            NaiveGumballMachineState.SOLD_OUT -> {
                result.append("sold out")
            }
            NaiveGumballMachineState.NO_QUARTER -> {
                result.append("waiting for quarter")
            }
            NaiveGumballMachineState.HAS_QUARTER -> {
                result.append("waiting for turn of crank")
            }
            NaiveGumballMachineState.SOLD -> {
                result.append("delivering a gumball")
            }
        }
        result.append("\n")
        return result.toString()
    }

    init {
        this.numberOfGumball = count
        if (count > 0u) {
            state = NaiveGumballMachineState.NO_QUARTER
        }
    }

    fun getBallCount(): UInt {
        return numberOfGumball
    }

    fun getQuarterCount(): UInt {
        return numberOfQuarters
    }
}