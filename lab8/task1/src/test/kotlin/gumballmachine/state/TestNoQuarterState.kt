package gumballmachine.state

import gumballmachine.GumballMachine
import gumballmachine.GumballMachineImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class TestNoQuarterState {
    private val outputStreamBuffer = ByteArrayOutputStream()
    private val outputStream = PrintStream(outputStreamBuffer)
    private var machine: GumballMachineImpl? = null
    private var state: GumballMachineState? = null

    @BeforeEach
    fun beforeEach() {
        machine = GumballMachineImpl(10u, outputStream)
        state = NoQuarterState(machine!!, outputStream)
    }

    @Test
    fun `ejectQuarter method doesn't change state`() {
        state!!.ejectQuarter()
        Assertions.assertTrue(machine!!.getState() is NoQuarterState)
        Assertions.assertEquals(10u, machine!!.getBallCount())
    }

    @Test
    fun `turnCrank method doesn't change state`() {
        state!!.turnCrank()
        Assertions.assertTrue(machine!!.getState() is NoQuarterState)
        Assertions.assertEquals(10u, machine!!.getBallCount())
    }
    
    @Test
    fun `insertQuarter method changes state to HasQuarterState`() {
        state!!.insertQuarter()
        Assertions.assertTrue(machine!!.getState() is HasQuarterState)
        Assertions.assertEquals(10u, machine!!.getBallCount())
    }

    @Test
    fun `Dispense method changes does nothing`() {
        state!!.dispense()
        Assertions.assertEquals(10u, machine!!.getBallCount())
        Assertions.assertTrue(machine!!.getState() is NoQuarterState)
    }
}