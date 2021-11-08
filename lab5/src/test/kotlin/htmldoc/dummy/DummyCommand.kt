package htmldoc.dummy

import htmldoc.command.Command

class DummyCommand(
    isExecuted: Boolean = false,
    isDisposed: Boolean = false
): Command {
    var isExecuted = isExecuted
        private set
    var isDisposed = isDisposed
        private set

    override fun execute() {
        isExecuted = true
    }

    override fun undo() {
        isExecuted = false
    }

    override fun dispose() {
        isDisposed = true
    }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is DummyCommand -> (other.isExecuted == isExecuted) && (other.isDisposed == isDisposed)
            else -> false
        }
    }

    override fun hashCode(): Int {
        var result = isExecuted.hashCode()
        result = 31 * result + isDisposed.hashCode()
        return result
    }
}