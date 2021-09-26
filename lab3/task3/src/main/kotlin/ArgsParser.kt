import chiper.DecryptInputStream
import chiper.EncryptOutputStream
import compression.CompressOutputStream
import compression.DecompressInputStream
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.util.*

fun generateDecorators(args: Array<String>): Pair<InputStream, OutputStream> {
    val argsQueue: Queue<String> = getArgsQueue(args.copyOfRange(0, args.size - 2))
    var inputStream: InputStream = FileInputStream(args[args.size - 2])
    var outputStream: OutputStream = FileOutputStream(args[args.size - 1])

    while (argsQueue.isNotEmpty()) {
        when(val currDecorator = getDecorator(argsQueue, inputStream, outputStream)) {
            is InputStream -> inputStream = currDecorator
            is OutputStream -> outputStream = currDecorator
        }
    }
    return Pair(inputStream, outputStream)
}

private fun getDecorator(argsQueue: Queue<String>, inputStream: InputStream, outputStream: OutputStream): Any {
    return when(argsQueue.poll()) {
        Operation.Decompress.commandName -> DecompressInputStream(inputStream)
        Operation.Decrypt.commandName -> DecryptInputStream(inputStream, argsQueue.poll().toLong())
        Operation.Compress.commandName -> CompressOutputStream(outputStream)
        Operation.Encrypt.commandName -> EncryptOutputStream(outputStream, argsQueue.poll().toLong())
        else -> throw IllegalArgumentException("Incorrect operation")
    }
}

private fun getArgsQueue(array: Array<String>): Queue<String> {
    val queue: Queue<String> = LinkedList()
    array.forEach { queue.add(it) }
    return queue
}
