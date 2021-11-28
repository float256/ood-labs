package gumballmachine.pattern.state

import gumballmachine.pattern.GumballMachineImpl
import gumballmachine.pattern.state.HasQuarterState
import gumballmachine.pattern.state.NoQuarterState
import gumballmachine.pattern.state.SoldOutState
import gumballmachine.pattern.state.SoldState
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class TestSoldState {
    private val outputStreamBuffer = ByteArrayOutputStream()
    private val outputStream = PrintStream(outputStreamBuffer)

    @Test
    fun `Quarter inserting does nothing`() {
        val machine = GumballMachineImpl(2u, outputStream)
        machine.insertQuarter()
        machine.setSoldState()

        machine.getState().insertQuarter()
        Assertions.assertEquals(2u, machine.getBallCount())
        Assertions.assertTrue(machine.getState() is SoldState)
    }

    @Test
    fun `Quarter ejecting does nothing`() {
        val machine = GumballMachineImpl(2u, outputStream)
        machine.insertQuarter()
        machine.setSoldState()

        machine.getState().ejectQuarter()
        Assertions.assertEquals(2u, machine.getBallCount())
        Assertions.assertTrue(machine.getState() is SoldState)
    }

    @Test
    fun `Turning crank does nothing`() {
        val machine = GumballMachineImpl(2u, outputStream)
        machine.insertQuarter()
        machine.setSoldState()

        machine.getState().turnCrank()
        Assertions.assertEquals(2u, machine.getBallCount())
        Assertions.assertTrue(machine.getState() is SoldState)
    }

    @Test
    fun `Dispense method changes state to NoQuarterState if there are more than one gumball and more than one quarter`() {
        val machine = GumballMachineImpl(2u, outputStream)
        machine.insertQuarter()
        machine.setSoldState()

        machine.getState().dispense()
        Assertions.assertEquals(1u, machine.getBallCount())
        Assertions.assertTrue(machine.getState() is NoQuarterState)
    }

    @Test
    fun `Dispense method changes state to HasQuarterState if there are more than one gumball and one quarter`() {
        val machine = GumballMachineImpl(2u, outputStream)
        machine.insertQuarter()
        machine.insertQuarter()
        machine.setSoldState()

        machine.getState().dispense()
        Assertions.assertEquals(1u, machine.getBallCount())
        Assertions.assertTrue(machine.getState() is HasQuarterState)
    }

    @Test
    fun `Dispense method changes state to SoldOutState if there is only one gumball`() {
        val machine = GumballMachineImpl(1u, outputStream)
        machine.insertQuarter()
        machine.insertQuarter()
        machine.setSoldState()

        machine.getState().dispense()
        Assertions.assertEquals(0u, machine.getBallCount())
        Assertions.assertTrue(machine.getState() is SoldOutState)
    }
}