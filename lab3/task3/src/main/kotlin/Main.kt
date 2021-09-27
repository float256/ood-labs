fun main(args: Array<String>) {
    val decoratorPair = generateDecorators(args)
    val inputStream = decoratorPair.first
    val outputStream = decoratorPair.second

    inputStream.transferTo(outputStream)
    outputStream.flush()
}
