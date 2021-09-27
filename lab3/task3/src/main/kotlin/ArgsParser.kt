import chiper.DecryptInputStream
import chiper.EncryptOutputStream
import compression.CompressOutputStream
import compression.DecompressInputStream
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.util.*
import kotlin.collections.ArrayList

fun generateDecorators(args: Array<String>): Pair<InputStream, OutputStream> {
    val argsQueue: Queue<String> = getArgsQueue(args.copyOfRange(0, args.size - 2))
    val inputStream: InputStream = getResultingInputDecorator(argsQueue, FileInputStream(args[args.size - 2]))
    val outputStream: OutputStream = getResultingOutputDecorator(argsQueue, FileOutputStream(args[args.size - 1]))

    return Pair(inputStream, outputStream)
}

private fun getResultingInputDecorator(argsQueue: Queue<String>, startingInputStream: InputStream): InputStream {
    var inputStream = startingInputStream
    while (argsQueue.isNotEmpty() && (getDecoratorType(argsQueue) == DecoratorType.Input)) {
        when (argsQueue.poll()) {
            Operation.Decompress.commandName -> {
                inputStream = DecompressInputStream(inputStream)
            }
            Operation.Decrypt.commandName -> {
                inputStream = DecryptInputStream(inputStream, argsQueue.poll().toLong())
            }
            else -> throw IllegalArgumentException("Incorrect command")
        }
    }
    return inputStream
}

private fun getResultingOutputDecorator(argsQueue: Queue<String>, startingOutputStream: OutputStream): OutputStream {
    val outputStreamConstructors = Stack<(OutputStream) -> OutputStream>()
    while (argsQueue.isNotEmpty() && (getDecoratorType(argsQueue) == DecoratorType.Output)) {
        when (argsQueue.poll()) {
            Operation.Compress.commandName -> {
                outputStreamConstructors.push { out: OutputStream ->
                    CompressOutputStream(out)
                }
            }
            Operation.Encrypt.commandName -> {
                val key = argsQueue.poll().toLong()
                outputStreamConstructors.push { out: OutputStream -> EncryptOutputStream(out, key) }
            }
            else -> {
                throw IllegalArgumentException()
            }
        }
    }

    var outputStream = startingOutputStream
    while (outputStreamConstructors.isNotEmpty()) {
        outputStream = outputStreamConstructors.pop()(outputStream)
    }
    return outputStream
}

private fun getArgsQueue(array: Array<String>): Queue<String> {
    val queue: Queue<String> = LinkedList()
    array.forEach { queue.add(it) }
    return queue
}

private fun getDecoratorType(argsQueue: Queue<String>): DecoratorType {
    return when (val currCommand = argsQueue.peek()) {
        Operation.Decompress.commandName -> DecoratorType.Input
        Operation.Compress.commandName -> DecoratorType.Output
        Operation.Encrypt.commandName -> DecoratorType.Output
        Operation.Decrypt.commandName -> DecoratorType.Input
        else -> throw IllegalArgumentException("Incorrect command '$currCommand'")
    }
}

private enum class DecoratorType {
    Input,
    Output
}

