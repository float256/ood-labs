package gumballmachine.pattern.state

import gumballmachine.pattern.GumballMachineImpl
import gumballmachine.pattern.state.GumballMachineState
import gumballmachine.pattern.state.SoldOutState
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class TestSoldOutState {
    private val outputStreamBuffer = ByteArrayOutputStream()
    private val outputStream = PrintStream(outputStreamBuffer)
    private var machine: GumballMachineImpl? = null
    private var state: GumballMachineState? = null

    @BeforeEach
    fun beforeEach() {
        machine = GumballMachineImpl(0u, outputStream)
        state = SoldOutState(machine!!, outputStream)
    }

    @Test
    fun `ejectQuarter method doesn't change state if there are no quarters in machine`() {
        state!!.ejectQuarter()
        Assertions.assertTrue(machine!!.getState() is SoldOutState)
        Assertions.assertEquals(0u, machine!!.getBallCount())
        Assertions.assertEquals(0u, machine!!.getQuarterCount())
    }

    @Test
    fun `ejectQuarter method ejects all quarters if there are quarters in machine`() {
        machine!!.addQuarter()
        machine!!.addQuarter()
        machine!!.addQuarter()
        state!!.ejectQuarter()
        Assertions.assertTrue(machine!!.getState() is SoldOutState)
        Assertions.assertEquals(0u, machine!!.getBallCount())
        Assertions.assertEquals(0u, machine!!.getQuarterCount())
    }

    @Test
    fun `turnCrank method doesn't change state`() {
        state!!.turnCrank()
        Assertions.assertTrue(machine!!.getState() is SoldOutState)
        Assertions.assertEquals(0u, machine!!.getBallCount())
        Assertions.assertEquals(0u, machine!!.getBallCount())
    }

    @Test
    fun `insertQuarter method doesn't change state`() {
        state!!.insertQuarter()
        Assertions.assertTrue(machine!!.getState() is SoldOutState)
        Assertions.assertEquals(0u, machine!!.getBallCount())
        Assertions.assertEquals(0u, machine!!.getBallCount())
    }

    @Test
    fun `Dispense method changes does nothing`() {
        state!!.dispense()
        Assertions.assertEquals(0u, machine!!.getBallCount())
        Assertions.assertTrue(machine!!.getState() is SoldOutState)
        Assertions.assertEquals(0u, machine!!.getBallCount())
    }
}