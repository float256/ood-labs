package htmldoc.command

abstract class OneExecutableCommand: Command {
    private var isExecuted: Boolean = false

    abstract fun executeImpl()
    abstract fun undoImpl()

    override fun execute() = when(isExecuted) {
        false -> {
            executeImpl()
            isExecuted = true
        }
        true -> {
            throw UnsupportedOperationException("This command can't be executed more than one time")
        }
    }

    override fun undo() = when(isExecuted) {
        true -> {
            undoImpl()
            isExecuted = false
        }
        false -> {
            throw UnsupportedOperationException("The command cannot be canceled because it has never been executed")
        }
    }

    protected fun isExecuted(): Boolean {
        return isExecuted
    }
}