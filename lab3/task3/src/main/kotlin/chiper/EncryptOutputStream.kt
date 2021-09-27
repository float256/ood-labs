package chiper

import java.io.OutputStream

class EncryptOutputStream(
    private val outputStream: OutputStream,
    private val key: Long
) : OutputStream() {
    override fun write(b: Int) {
        if (b != -1) {
            val encryptedSymbol = getSubstitutionTable(key).getValue(b)
            outputStream.write(encryptedSymbol)
        }
    }

    override fun flush() {
        outputStream.flush()
    }
}