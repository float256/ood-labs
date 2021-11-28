package gumballmachine.state

import gumballmachine.GumballMachineImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class TestHasQuarterState {
    private val outputStreamBuffer = ByteArrayOutputStream()
    private val outputStream = PrintStream(outputStreamBuffer)
    private var machine: GumballMachineImpl? = null
    private var state: GumballMachineState? = null

    @BeforeEach
    fun beforeEach() {
        machine = GumballMachineImpl(10u, outputStream)
        machine!!.insertQuarter()
        state = HasQuarterState(machine!!, outputStream)
    }

    @Test
    fun `Quarter inserting does nothing`() {
        state!!.insertQuarter()
        Assertions.assertEquals(10u, machine!!.getBallCount())
        Assertions.assertTrue(machine!!.getState() is HasQuarterState)
    }

    @Test
    fun `Quarter ejecting changes state to NoQuarterState`() {
        state!!.ejectQuarter()
        Assertions.assertEquals(10u, machine!!.getBallCount())
        Assertions.assertTrue(machine!!.getState() is NoQuarterState)
    }

    @Test
    fun `Turning crank changes state to SoldState`() {
        state!!.turnCrank()
        Assertions.assertEquals(10u, machine!!.getBallCount())
        Assertions.assertTrue(machine!!.getState() is SoldState)
    }

    @Test
    fun `Dispense method changes does nothing`() {
        state!!.dispense()
        Assertions.assertEquals(10u, machine!!.getBallCount())
        Assertions.assertTrue(machine!!.getState() is HasQuarterState)
    }
}