package htmldoc.resource

import htmldoc.file_manager.FileManager
import java.nio.file.Path
import java.util.*

class FileCopyContainer(
    private val resourceDirectory: Path,
    private val fileManager: FileManager
) : ResourceCopyContainer {
    private var copies = mutableMapOf<Path, Path>()
    private var markedForRemove = mutableSetOf<Path>()
    private var numberOfLinksOnCopy = mutableMapOf<Path, Int>()

    init {
        if (fileManager.isExists(resourceDirectory)) {
            if (!fileManager.isFolder(resourceDirectory)) {
                throw IllegalArgumentException("Incorrect path to resource directory")
            }
        } else {
            fileManager.createDirectory(resourceDirectory)
        }
    }

    override fun getCopy(pathToOriginal: Path): Path {
        return copies[pathToOriginal]!!
    }

    override fun copy(pathToOriginal: Path): Path {
        if (fileManager.isFolder(pathToOriginal)) {
            throw IllegalArgumentException("The specified path is not a file")
        }
        if (!copies.containsKey(pathToOriginal)) {
            copies[pathToOriginal] = resourceDirectory.resolve(
                UUID.randomUUID().toString() + getFileExtension(pathToOriginal)
            )
            fileManager.copyFile(pathToOriginal, copies[pathToOriginal]!!)
        }

        numberOfLinksOnCopy[pathToOriginal] = numberOfLinksOnCopy.getOrDefault(pathToOriginal, 0) + 1

        return copies[pathToOriginal]!!
    }

    override fun markCopyToRemove(pathToOriginal: Path) {
        markedForRemove.add(pathToOriginal)
    }

    override fun removeCopy(pathToOriginal: Path) {
        if (copies.containsKey(pathToOriginal)) {
            numberOfLinksOnCopy[pathToOriginal] = numberOfLinksOnCopy.getOrDefault(pathToOriginal, 1) - 1
            if (numberOfLinksOnCopy[pathToOriginal] == 0) {
                fileManager.removeFile(copies[pathToOriginal]!!)
                markedForRemove.remove(pathToOriginal)
                numberOfLinksOnCopy.remove(pathToOriginal)
                copies.remove(pathToOriginal)
            }
        } else {
            throw UnsupportedOperationException()
        }
    }

    override fun isCopyMarkedForRemove(pathToOriginal: Path): Boolean {
        return markedForRemove.contains(pathToOriginal)
    }

    override fun removeAllMarked() {
        markedForRemove.forEach { path -> removeCopy(path) }
    }

    private fun getFileExtension(path: Path): String {
        val fileName = path.fileName.toString()
        val fileParts = fileName.split(".")
        return if (fileParts.size > 1) fileParts.last() else ""
    }
}
