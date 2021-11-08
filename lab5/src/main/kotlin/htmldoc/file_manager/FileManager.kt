package htmldoc.file_manager

import java.nio.file.Path

interface FileManager {
    fun copyFile(from: Path, to: Path)
    fun remove(path: Path)
    fun isDirectory(path: Path): Boolean
    fun isExists(path: Path): Boolean
    fun createDirectory(path: Path)
    fun save(text: String, path: Path)
}