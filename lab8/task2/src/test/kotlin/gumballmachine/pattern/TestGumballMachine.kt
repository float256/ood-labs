package gumballmachine.pattern

import gumballmachine.pattern.GumballMachineImpl
import gumballmachine.pattern.state.NoQuarterState
import gumballmachine.pattern.state.SoldOutState
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestGumballMachine {
    @Test
    fun `Start state is NoQuarterState if there are gumballs in machine`() {
        val machine = GumballMachineImpl(1u, System.out)
        Assertions.assertTrue(machine.getState() is NoQuarterState)
    }

    @Test
    fun `Start state is SoldOutState if there aren't gumballs in machine`() {
        val machine = GumballMachineImpl(0u, System.out)
        Assertions.assertTrue(machine.getState() is SoldOutState)
    }

    @Test
    fun `Release ball decrease number of gumballs if there are gumballs in machine`() {
        val machine = GumballMachineImpl(1u, System.out)
        machine.insertQuarter()
        machine.releaseBall()
        Assertions.assertEquals(0u, machine.getBallCount())
    }

    @Test
    fun `Release ball decrease number of gumballs if there aren't gumballs in machine`() {
        val machine = GumballMachineImpl(0u, System.out)
        machine.insertQuarter()
        machine.releaseBall()
        Assertions.assertEquals(0u, machine.getBallCount())
    }
}