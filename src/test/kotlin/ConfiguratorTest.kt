import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ConfiguratorTest {

    @Test
    fun readConfigFromFile1() {
        val (numPlayers, board) = Configurator.readConfigFromFile("src/test/resources/tileboard1")
        assertEquals(2, numPlayers)
        assertEquals(3, board.rowCount())
        assertEquals(BoardRow.LeftGood, board.rowAt(0))
        assertEquals(BoardRow.RightGood, board.rowAt(1))
        assertEquals(BoardRow.RightGood, board.rowAt(2))
    }

    @Test
    fun readConfigFromBadFile1() {
        assertThrows(NumberFormatException::class.java) {
            Configurator.readConfigFromFile("src/test/resources/tileboard-bad")
        }
    }

    @Test
    fun readConfigFromBadFile2() {
        assertThrows(InvalidConfigFileException::class.java) {
            Configurator.readConfigFromFile("src/test/resources/tileboard-bad2")
        }
    }

}
