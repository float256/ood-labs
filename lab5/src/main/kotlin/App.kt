import htmldoc.HtmlDoc
import htmldoc.HtmlDocImpl
import htmldoc.htmlgenerator.HtmlGeneratorImpl
import htmldoc.item.ImageItem
import htmldoc.item.ParagraphItem
import htmldoc.resource.FileCopyContainer
import htmldoc.file_manager.FileManager
import htmldoc.file_manager.FileManagerImpl
import java.io.InputStream
import java.io.PrintStream
import java.nio.file.Path
import java.util.*

class App(
    private val inputStream: InputStream,
    private val outputStream: PrintStream,
) {
    companion object {
        private const val END_POSITION = "end"
        private const val INPUT_PROMPT = ">> "
    }

    fun processInput() {
        val input = Scanner(inputStream)
        val doc = createHtmlDoc(input, outputStream)
        while(true) {
            outputStream.print(INPUT_PROMPT)
            if (input.hasNextLine()) {
                val currLine = input.nextLine()
                try {
                    processOneLine(currLine, doc, outputStream)
                } catch(e: Exception) {
                    outputStream.println("ERROR: $e")
                }
            } else {
                break
            }
        }
    }

    private fun createHtmlDoc(input: Scanner, output: PrintStream): HtmlDoc {
        output.print("Input path to copies for images: ")
        if (input.hasNextLine()) {
            val fileManager: FileManager = FileManagerImpl()
            val copyContainer = FileCopyContainer(Path.of(input.nextLine()), fileManager)
            val htmlGenerator = HtmlGeneratorImpl()
            return HtmlDocImpl(copyContainer, htmlGenerator, fileManager)
        } else {
            throw IllegalArgumentException("Path to folder for copies does not provided")
        }
    }

    private fun processOneLine(line: String, doc: HtmlDoc, output: PrintStream) {
        val args = line.split(" ").toMutableList()
        if (args.isEmpty()) {
            return
        } else {
            when(args[0]){
                AvailableCommands.InsertParagraph.name -> insertParagraph(args, doc)
                AvailableCommands.InsertImage.name -> insertImage(args, doc)
                AvailableCommands.SetTitle.name -> setTitle(args, doc)
                AvailableCommands.List.name -> list(doc, output)
                AvailableCommands.ReplaceText.name -> replaceText(args, doc)
                AvailableCommands.ResizeImage.name -> resizeImage(args, doc)
                AvailableCommands.DeleteItem.name -> deleteItem(args, doc)
                AvailableCommands.Help.name -> help(output)
                AvailableCommands.Undo.name -> undo(doc)
                AvailableCommands.Redo.name -> redo(doc)
                AvailableCommands.Save.name -> save(args, doc)
                else -> throw IllegalArgumentException("Incorrect command")
            }
        }
    }

    private fun insertParagraph(args: List<String>, doc: HtmlDoc) {
        assertIsCorrectNumberOfArgs(args, 3)
        val position = getPositionFromString(args[1], doc)
        val text = args[2]
        doc.insertParagraph(text, position)
    }

    private fun insertImage(args: List<String>, doc: HtmlDoc) {
        assertIsCorrectNumberOfArgs(args, 5)
        val position = getPositionFromString(args[1], doc)
        val width = args[2].toInt()
        val height = args[3].toInt()
        val path = Path.of(args[4])
        doc.insertImage(width, height, path, position)
    }

    private fun setTitle(args: List<String>, doc: HtmlDoc) {
        assertIsCorrectNumberOfArgs(args, 2)
        doc.title = args[1]
    }

    private fun list(doc: HtmlDoc, output: PrintStream) {
        output.println("Title: ${doc.title}")
        (0 until doc.getNumberOfItems()).forEach { idx ->
            output.println("$idx: ${doc.getItem(idx).description}")
        }
    }

    private fun replaceText(args: List<String>, doc: HtmlDoc) {
        assertIsCorrectNumberOfArgs(args, 3)
        val position = getPositionFromString(args[1], doc)
        val newText = args[2]
        when(val item = doc.getItem(position)) {
            is ParagraphItem -> item.changeText(newText)
            else -> throw IllegalArgumentException("Selected item is not a paragraph")
        }
    }

    private fun resizeImage(args: List<String>, doc: HtmlDoc) {
        assertIsCorrectNumberOfArgs(args, 4)
        val position = getPositionFromString(args[1], doc)
        val width = args[2].toInt()
        val height = args[3].toInt()
        when(val item = doc.getItem(position)) {
            is ImageItem -> item.resize(width, height)
            else -> throw IllegalArgumentException("Selected item is not a paragraph")
        }
    }

    private fun deleteItem(args: List<String>, doc: HtmlDoc) {
        assertIsCorrectNumberOfArgs(args, 2)
        val position = getPositionFromString(args[1], doc)
        doc.deleteItem(position)
    }

    private fun help(output: PrintStream) {
        output.println(""" Allowed commands:
                -- InsertParagraph <position>|end <text>
                -- InsertImage <position>|end <width> <height> <path_to_image>
                -- SetTitle <title>
                -- List
                -- ReplaceText <position> <text>
                -- ResizeImage <position> <width> <height>
                -- DeleteItem <position>
                -- Help
                -- Undo
                -- Redo
                -- Save <path>""".trimIndent())
    }

    private fun undo(doc: HtmlDoc) {
        doc.undo()
    }


    private fun redo(doc: HtmlDoc) {
        doc.redo()
    }

    private fun save(args: List<String>, doc: HtmlDoc) {
        assertIsCorrectNumberOfArgs(args, 2)
        doc.save(Path.of(args[1]))
    }

    private fun assertIsCorrectNumberOfArgs(args: List<String>, numberOfArguments: Int) {
        if (args.size != numberOfArguments) {
            throw IllegalArgumentException("Incorrect number of arguments")
        }
    }

    private fun getPositionFromString(position: String, doc: HtmlDoc): Int {
        return if (position == END_POSITION) doc.getNumberOfItems() else position.toInt()
    }
}