package htmldoc.command

import htmldoc.dummy.DummyOneExecutableCommand
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class TestOneExecutableCommand {
    @Test
    fun `Trying to execute test twice throws exception`() {
        val oneExecutableCommand = DummyOneExecutableCommand()
        oneExecutableCommand.execute()
        assertThrows<Exception> { oneExecutableCommand.execute() }
    }

    @Test
    fun `Trying to revert test twice throws exception`() {
        val oneExecutableCommand = DummyOneExecutableCommand()
        oneExecutableCommand.execute()
        oneExecutableCommand.undo()
        assertThrows<Exception> { oneExecutableCommand.undo() }
    }
}