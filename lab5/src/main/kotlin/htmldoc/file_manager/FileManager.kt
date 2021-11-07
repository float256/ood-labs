package htmldoc.file_manager

import java.nio.file.Path

interface FileManager {
    fun copyFile(from: Path, to: Path)
    fun removeFile(path: Path)
    fun isFolder(path: Path): Boolean
    fun isExists(path: Path): Boolean
    fun createDirectory(path: Path)
    fun save(text: String, path: Path)
}