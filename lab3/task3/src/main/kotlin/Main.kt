import java.io.InputStream
import java.io.OutputStream

fun main(args: Array<String>) {
    val decoratorPair = generateDecorators(args)
    val inputStream = decoratorPair.first
    val outputStream = decoratorPair.second

    write(inputStream, outputStream)
    outputStream.flush()
}

fun write(input: InputStream, output: OutputStream) {
    var currSymbol = 0
    while (currSymbol != -1) {
        currSymbol = input.read()
        if (currSymbol != -1) {
            output.write(currSymbol)
        }
    }
}
