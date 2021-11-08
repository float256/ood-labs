package htmldoc.resource

import htmldoc.dummy.DummyFileManager
import htmldoc.file_manager.FileManagerImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.io.path.Path

class FileCopyContainerTests {
    private lateinit var fileManager: DummyFileManager
    private lateinit var container: ResourceCopyContainer
    private val selectedFolder = Path("/root")

    @BeforeEach
    fun beforeEachTest() {
        fileManager = DummyFileManager()
        container = FileCopyContainer(selectedFolder, fileManager)
    }

    @Test
    fun `Copy of one file works correctly`() {
        val original = Path("/home/user/123.txt")
        val copy = container.copy(original)

        Assertions.assertEquals(fileManager.allElements[copy], DummyFileManager.ElementType.File)
        Assertions.assertEquals(fileManager.allElements.size, 2)
    }

    @Test
    fun `Copy is on selected directory`() {
        val original = Path("/home/user/123.txt")
        val copy = container.copy(original)

        Assertions.assertEquals(selectedFolder, copy.parent)
    }

    @Test
    fun `Copy of one file twice creates only one copy`() {
        val original = Path("/home/user/123.txt")
        val firstCopy = container.copy(original)
        val secondCopy = container.copy(original)

        Assertions.assertEquals(firstCopy, secondCopy)
    }

    @Test
    fun `Copy of two different files creates two different copies`() {
        val firstOriginal = Path("/home/user/123.txt")
        val firstCopy = container.copy(firstOriginal)

        val secondOriginal = Path("/home/user/1234.txt")
        val secondCopy = container.copy(secondOriginal)

        Assertions.assertNotEquals(firstCopy, secondCopy)
    }

    @Test
    fun `Marking copy to removing works correctly`() {
        val original = Path("/home/user/123.txt")
        val copy = container.copy(original)
        container.markCopyToRemove(original)

        Assertions.assertEquals(fileManager.allElements[copy], DummyFileManager.ElementType.File)
        Assertions.assertEquals(fileManager.allElements.size, 2)
        Assertions.assertTrue(container.isCopyMarkedForRemove(original))
    }

    @Test
    fun `Remove marked copies works correctly`() {
        val original = Path("/home/user/123.txt")
        container.copy(original)
        container.markCopyToRemove(original)
        container.removeAllMarked()

        Assertions.assertTrue(fileManager.allElements.size == 1)
    }

    @Test
    fun `Remove unmarked copy also removes it`() {
        val original = Path("/home/user/123.txt")
        container.copy(original)
        container.removeCopy(original)

        Assertions.assertTrue(fileManager.allElements.size == 1)
    }

    @Test
    fun `Trying to use file as a folder for copies throws exception`() {
        assertThrows<Exception> {
            fileManager.copyFile(Path("/home/user/.txt"), Path("/home/user/123.txt"))
            FileCopyContainer(Path("/home/user/123.txt"), fileManager)
        }
    }

    @Test
    fun `Trying to copy folder throws exception`() {
        assertThrows<Exception> {
            fileManager.createDirectory(Path("/home/user/123"))
            container.copy(Path("/home/user/123"))
        }
    }

    @Test
    fun `Trying to remove file which doesn't exists`() {
        assertThrows<Exception> {
            container.removeCopy(Path("/123"))
        }
    }

    @Test
    fun `Trying to create container in path where file exists`() {
        assertThrows<Exception> {
            val fileManager = FileManagerImpl()
            val testedPath = Path("/123")
            fileManager.copyFile(testedPath, Path("/copy"))
            FileCopyContainer(testedPath, fileManager)
        }
    }
}