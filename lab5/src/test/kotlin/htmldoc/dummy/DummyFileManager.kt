package htmldoc.dummy

import htmldoc.file_manager.FileManager
import java.nio.file.Path

class DummyFileManager: FileManager {
    enum class ElementType {
        File, Directory
    }

    val allElements = mutableMapOf<Path, ElementType>()

    override fun copyFile(from: Path, to: Path) {
        if (!isExists(to)) {
            allElements[to] = ElementType.File
        } else {
            throw IllegalArgumentException()
        }
    }

    override fun remove(path: Path) {
        allElements.remove(path)
    }

    override fun isDirectory(path: Path): Boolean {
        return (path in allElements) && (allElements[path] == ElementType.Directory)
    }

    override fun isExists(path: Path): Boolean {
        return (path in allElements)
    }

    override fun createDirectory(path: Path) {
        if (!isExists(path)) {
            allElements[path] = ElementType.Directory
        } else {
            throw IllegalArgumentException()
        }
    }

    override fun save(text: String, path: Path) {
        if (!isExists(path)) {
            allElements[path] = ElementType.File
        } else {
            throw IllegalArgumentException()
        }
    }
}