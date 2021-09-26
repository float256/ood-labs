package compression

import java.io.InputStream
import java.util.*

class DecompressInputStream(
    private val inputStream: InputStream
): InputStream() {
    private val symbolQueue: Queue<Int> = LinkedList()

    override fun read(): Int {
        if (symbolQueue.isEmpty()) {
            val numberOfRecurring = inputStream.read()
            val currSymbol = inputStream.read()

            if (isEndOfStream(currSymbol)) {
                readBlock(numberOfRecurring, currSymbol)
            }
        }
        return when(val returnedSymbol = symbolQueue.poll()) {
            null -> -1
            else -> returnedSymbol.toInt()
        }
    }

    private fun readBlock(numberOfRecurring: Int, currSymbol: Int) {
        repeat(numberOfRecurring) {
            symbolQueue.add(currSymbol)
        }
    }

    private fun isEndOfStream(currSymbol: Int): Boolean {
        return currSymbol != -1
    }
}