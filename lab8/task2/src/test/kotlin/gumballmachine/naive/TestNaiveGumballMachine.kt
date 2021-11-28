package gumballmachine.naive

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestNaiveGumballMachine {
    @Test
    fun `In state HAS_QUARTER quarter inserting adds new quarter if it is not the maximum number`() {
        val machine = NaiveGumballMachine(10u)

        machine.insertQuarter()
        machine.insertQuarter()
        machine.insertQuarter()
        Assertions.assertEquals(10u, machine.getBallCount())
        Assertions.assertEquals(3u, machine.getQuarterCount())
        Assertions.assertEquals(machine.state,  NaiveGumballMachineState.HAS_QUARTER)
    }

    @Test
    fun `In state HAS_QUARTER quarter inserting does nothing if it is the maximum number`() {
        val machine = NaiveGumballMachine(10u)

        machine.insertQuarter()
        machine.insertQuarter()
        machine.insertQuarter()
        machine.insertQuarter()
        machine.insertQuarter()

        Assertions.assertEquals(10u, machine.getBallCount())
        Assertions.assertEquals(5u, machine.getQuarterCount())
        Assertions.assertEquals(machine.state,  NaiveGumballMachineState.HAS_QUARTER)
    }

    @Test
    fun `In state HAS_QUARTER quarter ejecting changes state to NoQuarterState`() {
        val machine = NaiveGumballMachine(10u)
        machine.insertQuarter()

        machine.ejectQuarter()
        Assertions.assertEquals(10u, machine.getBallCount())
        Assertions.assertEquals(machine.state,  NaiveGumballMachineState.NO_QUARTER)
        Assertions.assertEquals(0u, machine.getQuarterCount())
    }

    @Test
    fun `In state HAS_QUARTER turning crank changes state to SoldState`() {
        val machine = NaiveGumballMachine(10u)
        machine.insertQuarter()
        
        machine.turnCrank()
        Assertions.assertEquals(10u, machine.getBallCount())
        Assertions.assertEquals(machine.state,  NaiveGumballMachineState.SOLD)
        Assertions.assertEquals(1u, machine.getQuarterCount())
    }

    @Test
    fun `In state HAS_QUARTER dispense method changes does nothing`() {
        val machine = NaiveGumballMachine(10u)
        machine.insertQuarter()
        
        machine.dispense()
        Assertions.assertEquals(10u, machine.getBallCount())
        Assertions.assertEquals(machine.state,  NaiveGumballMachineState.HAS_QUARTER)
        Assertions.assertEquals(1u, machine.getQuarterCount())
    }
    
    @Test
    fun `In state NO_QUARTER ejectQuarter method doesn't change state`() {
        val machine = NaiveGumballMachine(10u)

        machine.ejectQuarter()
        Assertions.assertEquals(machine.state,  NaiveGumballMachineState.NO_QUARTER)
        Assertions.assertEquals(10u, machine.getBallCount())
        Assertions.assertEquals(0u, machine.getQuarterCount())
    }

    @Test
    fun `In state NO_QUARTER turnCrank method doesn't change state`() {
        val machine = NaiveGumballMachine(10u)

        machine.turnCrank()
        Assertions.assertEquals(machine.state,  NaiveGumballMachineState.NO_QUARTER)
        Assertions.assertEquals(10u, machine.getBallCount())
        Assertions.assertEquals(0u, machine.getQuarterCount())
    }

    @Test
    fun `In state NO_QUARTER insertQuarter method changes state to HasQuarterState`() {
        val machine = NaiveGumballMachine(10u)

        machine.insertQuarter()
        Assertions.assertEquals(machine.state,  NaiveGumballMachineState.HAS_QUARTER)
        Assertions.assertEquals(10u, machine.getBallCount())
        Assertions.assertEquals(1u, machine.getQuarterCount())
    }

    @Test
    fun `In state NO_QUARTER dispense method changes does nothing`() {
        val machine = NaiveGumballMachine(10u)

        machine.dispense()
        Assertions.assertEquals(10u, machine.getBallCount())
        Assertions.assertEquals(machine.state,  NaiveGumballMachineState.NO_QUARTER)
        Assertions.assertEquals(0u, machine.getQuarterCount())
    }

    @Test
    fun `In state SOLD_OUT ejectQuarter method doesn't change state if there are no quarters in machine`() {
        val machine = NaiveGumballMachine(0u)
        
        machine.ejectQuarter()
        Assertions.assertEquals(machine.state,  NaiveGumballMachineState.SOLD_OUT)
        Assertions.assertEquals(0u, machine.getBallCount())
        Assertions.assertEquals(0u, machine.getQuarterCount())
    }

    @Test
    fun `In state SOLD_OUT ejectQuarter method ejects all quarters if there are quarters in machine`() {
        val machine = NaiveGumballMachine(0u)

        machine.insertQuarter()
        machine.insertQuarter()
        machine.insertQuarter()
        machine.ejectQuarter()

        Assertions.assertEquals(machine.state,  NaiveGumballMachineState.SOLD_OUT)
        Assertions.assertEquals(0u, machine.getBallCount())
        Assertions.assertEquals(0u, machine.getQuarterCount())
    }

    @Test
    fun `In state SOLD_OUT turnCrank method doesn't change state`() {
        val machine = NaiveGumballMachine(0u)

        machine.turnCrank()
        Assertions.assertEquals(machine.state,  NaiveGumballMachineState.SOLD_OUT)
        Assertions.assertEquals(0u, machine.getBallCount())
        Assertions.assertEquals(0u, machine.getBallCount())
    }

    @Test
    fun `In state SOLD_OUT insertQuarter method doesn't change state`() {
        val machine = NaiveGumballMachine(0u)

        machine.insertQuarter()
        Assertions.assertEquals(machine.state,  NaiveGumballMachineState.SOLD_OUT)
        Assertions.assertEquals(0u, machine.getBallCount())
        Assertions.assertEquals(0u, machine.getBallCount())
    }

    @Test
    fun `In state SOLD_OUT dispense method changes does nothing`() {
        val machine = NaiveGumballMachine(0u)

        machine.dispense()
        Assertions.assertEquals(0u, machine.getBallCount())
        Assertions.assertEquals(machine.state,  NaiveGumballMachineState.SOLD_OUT)
        Assertions.assertEquals(0u, machine.getBallCount())
    }
    
    @Test
    fun `In state SOLD_OUT quarter inserting does nothing`() {
        val machine = NaiveGumballMachine(2u)
        machine.insertQuarter()
        machine.state = NaiveGumballMachineState.SOLD

        machine.insertQuarter()
        Assertions.assertEquals(2u, machine.getBallCount())
        Assertions.assertEquals(machine.state,  NaiveGumballMachineState.SOLD)
    }

    @Test
    fun `In state SOLD_OUT quarter ejecting does nothing`() {
        val machine = NaiveGumballMachine(2u)
        machine.insertQuarter()
        machine.state = NaiveGumballMachineState.SOLD

        machine.ejectQuarter()
        Assertions.assertEquals(2u, machine.getBallCount())
        Assertions.assertEquals(machine.state,  NaiveGumballMachineState.SOLD)
    }

    @Test
    fun `In state SOLD_OUT turning crank does nothing`() {
        val machine = NaiveGumballMachine(2u)
        machine.insertQuarter()
        machine.state = NaiveGumballMachineState.SOLD

        machine.turnCrank()
        Assertions.assertEquals(2u, machine.getBallCount())
        Assertions.assertEquals(machine.state,  NaiveGumballMachineState.SOLD)
    }

    @Test
    fun `In state SOLD_OUT dispense method changes state to NoQuarterState if there are more than one gumball and more than one quarter`() {
        val machine = NaiveGumballMachine(2u)
        machine.insertQuarter()
        machine.state = NaiveGumballMachineState.SOLD

        machine.dispense()
        Assertions.assertEquals(1u, machine.getBallCount())
        Assertions.assertEquals(machine.state,  NaiveGumballMachineState.NO_QUARTER)
    }

    @Test
    fun `In state SOLD_OUT dispense method changes state to HasQuarterState if there are more than one gumball and one quarter`() {
        val machine = NaiveGumballMachine(2u)
        machine.insertQuarter()
        machine.insertQuarter()
        machine.state = NaiveGumballMachineState.SOLD

        machine.dispense()
        Assertions.assertEquals(1u, machine.getBallCount())
        Assertions.assertEquals(machine.state,  NaiveGumballMachineState.HAS_QUARTER)
    }

    @Test
    fun `In state SOLD_OUT dispense method changes state to SoldOutState if there is only one gumball`() {
        val machine = NaiveGumballMachine(1u)
        machine.insertQuarter()
        machine.insertQuarter()
        machine.state = NaiveGumballMachineState.SOLD

        machine.dispense()
        Assertions.assertEquals(0u, machine.getBallCount())
        Assertions.assertEquals(machine.state,  NaiveGumballMachineState.SOLD_OUT)
    }
}