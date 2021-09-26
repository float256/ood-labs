package compression

import java.io.OutputStream
import java.util.*

class CompressOutputStream(
    private val outputStream: OutputStream
): OutputStream() {
    private val buffer: MutableList<Int> = LinkedList()
    private val indexOfNumberOfRecurring = 0
    private var indexOfCurrentSymbol = 1

    override fun write(b: Int) {
        if (buffer.isEmpty()) {
            addSymbolToBuffer(b)
        } else if ((b != buffer[indexOfCurrentSymbol]) || (buffer[indexOfNumberOfRecurring] >= Constants.MaxBlockSize)) {
            writeAllSymbols()
            addSymbolToBuffer(b)
        } else {
            buffer[indexOfNumberOfRecurring]++
        }
    }

    override fun flush() {
        writeAllSymbols()
        outputStream.flush()
    }

    private fun writeAllSymbols() {
        buffer.forEach {
            outputStream.write(it)
        }
        buffer.clear()
    }

    private fun addSymbolToBuffer(symbol: Int) {
        buffer.addAll(0, mutableListOf(1, symbol))
    }
}