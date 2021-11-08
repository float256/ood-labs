package htmldoc.history

import htmldoc.HtmlDocConstants
import htmldoc.dummy.DummyCommand
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TestHistoryImpl {
    @Test
    fun `Test command execution`() {
        val command = DummyCommand()
        val history = HistoryImpl()
        history.addAndExecuteCommand(command)

        assertTrue(command.isExecuted)
        assertFalse(command.isDisposed)
    }

    @Test
    fun `Test command reverting`() {
        val command = DummyCommand()
        val history = HistoryImpl()
        history.addAndExecuteCommand(command)
        history.undo()

        assertFalse(command.isExecuted)
        assertFalse(command.isDisposed)
    }

    @Test
    fun `Trying to revert command if it can't be done throws exception`() {
        val command = DummyCommand()
        val history = HistoryImpl()
        history.addAndExecuteCommand(command)

        history.undo()
        assertThrows<Exception> { history.undo() }
    }

    @Test
    fun `Trying to redo command if it can't be done throws exception`() {
        val command = DummyCommand()
        val history = HistoryImpl()
        history.addAndExecuteCommand(command)

        history.undo()
        history.redo()
        assertThrows<Exception> { history.redo() }
    }

    @Test
    fun `Test command is disposed after going beyond history boundaries`() {
        val history = HistoryImpl()
        val firstCommand = DummyCommand()
        history.addAndExecuteCommand(firstCommand)
        repeat((1..HtmlDocConstants.HISTORY_MAX_LENGTH).count()) { history.addAndExecuteCommand(DummyCommand()) }

        assertTrue(firstCommand.isExecuted)
        assertTrue(firstCommand.isDisposed)
    }

    @Test
    fun `Test rewriting history`() {
        val history = HistoryImpl()
        val erasedCommand = DummyCommand()
        history.addAndExecuteCommand(erasedCommand)
        history.undo()
        history.addAndExecuteCommand(DummyCommand())

        assertFalse(erasedCommand.isExecuted)
        assertTrue(erasedCommand.isDisposed)
    }
}