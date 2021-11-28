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
    fun `Quarter inserting adds new quarter if it is not the maximum number`() {
        state!!.insertQuarter()
        state!!.insertQuarter()
        state!!.insertQuarter()
        Assertions.assertEquals(10u, machine!!.getBallCount())
        Assertions.assertEquals(4u, machine!!.getQuarterCount())
        Assertions.assertTrue(machine!!.getState() is HasQuarterState)
    }

    @Test
    fun `Quarter inserting does nothing if it is the maximum number`() {
        state!!.insertQuarter()
        state!!.insertQuarter()
        state!!.insertQuarter()
        state!!.insertQuarter()
        state!!.insertQuarter()

        Assertions.assertEquals(10u, machine!!.getBallCount())
        Assertions.assertEquals(5u, machine!!.getQuarterCount())
        Assertions.assertTrue(machine!!.getState() is HasQuarterState)
    }

    @Test
    fun `Quarter ejecting changes state to NoQuarterState`() {
        state!!.ejectQuarter()
        Assertions.assertEquals(10u, machine!!.getBallCount())
        Assertions.assertTrue(machine!!.getState() is NoQuarterState)
        Assertions.assertEquals(0u, machine!!.getQuarterCount())
    }

    @Test
    fun `Turning crank changes state to SoldState`() {
        state!!.turnCrank()
        Assertions.assertEquals(10u, machine!!.getBallCount())
        Assertions.assertTrue(machine!!.getState() is SoldState)
        Assertions.assertEquals(1u, machine!!.getQuarterCount())
    }

    @Test
    fun `Dispense method changes does nothing`() {
        state!!.dispense()
        Assertions.assertEquals(10u, machine!!.getBallCount())
        Assertions.assertTrue(machine!!.getState() is HasQuarterState)
        Assertions.assertEquals(1u, machine!!.getQuarterCount())
    }

    @Test
    fun `Filling machine changes only number of balls`() {
        state!!.fillMachine(10u)
        Assertions.assertEquals(20u, machine!!.getBallCount())
        Assertions.assertTrue(machine!!.getState() is HasQuarterState)
        Assertions.assertEquals(1u, machine!!.getQuarterCount())
    }
}