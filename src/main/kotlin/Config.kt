import java.io.File

data class Config(val numPlayers: Int, val board: TileBoard)

class InvalidConfigFileException(msg: String) : Exception(msg)

object Configurator {

    fun readConfigFromFile(filePath: String): Config = File(filePath).useLines { lines ->

        var playerCount: Int = 0
        val board = MutableTileBoard()
        lines.forEachIndexed { n, line ->
            if (n == 0) {
                playerCount = line.toInt()
            } else {
                when (line.trim()) {
                    "OX" -> board.addRow(BoardRow.RightGood)
                    "XO" -> board.addRow(BoardRow.LeftGood)
                    "" -> {
                    } // ignore empty lines
                    else -> throw InvalidConfigFileException("Board row '$line' is not a valid row")
                }
            }
        }

        return Config(playerCount, board)
    }
}
