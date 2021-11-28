package gumballmachine.state

import gumballmachine.GumballMachineImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class TestSoldOutState {
    private val outputStreamBuffer = ByteArrayOutputStream()
    private val outputStream = PrintStream(outputStreamBuffer)
    private var machine: GumballMachineImpl? = null

    @BeforeEach
    fun beforeEach() {
        machine = GumballMachineImpl(0u, outputStream)
        machine!!.setSoldOutState()
    }

    @Test
    fun `ejectQuarter method doesn't change state if there are no quarters in machine`() {
        machine!!.getState().ejectQuarter()
        Assertions.assertTrue(machine!!.getState() is SoldOutState)
        Assertions.assertEquals(0u, machine!!.getBallCount())
        Assertions.assertEquals(0u, machine!!.getQuarterCount())
    }

    @Test
    fun `ejectQuarter method ejects all quarters if there are quarters in machine`() {
        machine!!.addQuarter()
        machine!!.addQuarter()
        machine!!.addQuarter()
        machine!!.getState().ejectQuarter()
        Assertions.assertTrue(machine!!.getState() is SoldOutState)
        Assertions.assertEquals(0u, machine!!.getBallCount())
        Assertions.assertEquals(0u, machine!!.getQuarterCount())
    }

    @Test
    fun `turnCrank method doesn't change state`() {
        machine!!.getState().turnCrank()
        Assertions.assertTrue(machine!!.getState() is SoldOutState)
        Assertions.assertEquals(0u, machine!!.getBallCount())
        Assertions.assertEquals(0u, machine!!.getBallCount())
    }

    @Test
    fun `insertQuarter method doesn't change state`() {
        machine!!.getState().insertQuarter()
        Assertions.assertTrue(machine!!.getState() is SoldOutState)
        Assertions.assertEquals(0u, machine!!.getBallCount())
        Assertions.assertEquals(0u, machine!!.getBallCount())
    }

    @Test
    fun `Dispense method changes does nothing`() {
        machine!!.getState().dispense()
        Assertions.assertEquals(0u, machine!!.getBallCount())
        Assertions.assertTrue(machine!!.getState() is SoldOutState)
        Assertions.assertEquals(0u, machine!!.getBallCount())
    }

    @Test
    fun `Filling machine changes state to HasQuarterState if there are quarters in machine`() {
        machine!!.addQuarter()
        machine!!.fillMachine(10u)

        Assertions.assertEquals(10u, machine!!.getBallCount())
        Assertions.assertTrue(machine!!.getState() is HasQuarterState)
        Assertions.assertEquals(1u, machine!!.getQuarterCount())
    }

    @Test
    fun `Filling machine changes state to NoQuarterState if there aren't quarters in machine`() {
        machine!!.fillMachine(10u)

        Assertions.assertEquals(10u, machine!!.getBallCount())
        Assertions.assertTrue(machine!!.getState() is NoQuarterState)
        Assertions.assertEquals(0u, machine!!.getQuarterCount())
    }
}