package htmldoc.file_manager

import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.exists

class FileManagerImpl: FileManager {
    override fun copyFile(from: Path, to: Path) {
        Files.copy(from, to)
    }

    override fun remove(path: Path) {
        path.toFile().delete()
    }

    override fun isDirectory(path: Path): Boolean {
        return path.toFile().isDirectory
    }

    override fun isExists(path: Path): Boolean {
        return path.exists()
    }

    override fun createDirectory(path: Path) {
        try {
            Files.createDirectory(path)
        } catch (e: IOException) {
            throw IllegalArgumentException("Directory can't be created")
        }
    }

    override fun save(text: String, path: Path) {
        try {
            Files.writeString(path, text)
        } catch (e: IOException) {
            throw IllegalArgumentException("File can't be created")
        }
    }
}