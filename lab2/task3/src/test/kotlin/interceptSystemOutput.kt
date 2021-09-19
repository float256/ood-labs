import java.io.ByteArrayOutputStream
import java.io.PrintStream


fun interceptSystemOutput(func: () -> Unit): String {
    val stdout = System.out
    val byteOutputStream = ByteArrayOutputStream()
    System.setOut(PrintStream(byteOutputStream))

    try {
        func()
    } finally {
        System.setOut(stdout)
    }
    return byteOutputStream.toString()
}
