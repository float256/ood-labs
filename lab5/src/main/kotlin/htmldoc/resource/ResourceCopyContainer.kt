package htmldoc.resource

import java.nio.file.Path

interface ResourceCopyContainer {
    fun getCopy(pathToOriginal: Path): Path
    fun copy(pathToOriginal: Path): Path
    fun markCopyToRemove(pathToOriginal: Path)
    fun removeCopy(pathToOriginal: Path)
    fun isCopyMarkedForRemove(pathToOriginal: Path): Boolean
    fun removeAllMarked()
}