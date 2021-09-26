package chiper

import java.io.InputStream

class DecryptInputStream(
    private val inputStream: InputStream,
    private val key: Long
): InputStream() {
    override fun read(): Int {
        return when(val currSymbol = inputStream.read()) {
            -1 -> -1
            else -> getReversedSubstitutionMap(key).getValue(currSymbol)
        }
    }
}