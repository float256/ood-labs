package htmldoc.dummy

import htmldoc.command.OneExecutableCommand

class DummyOneExecutableCommand: OneExecutableCommand() {
    override fun executeImpl() {}

    override fun undoImpl() {}
}