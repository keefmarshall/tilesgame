/**
 * This doesn't scale well if we ever wanted to play with more than
 * two lines of tiles!
 */
enum class BoardRow { LeftGood, RightGood }

/**
 * TileBoard is the template board, it's an immutable
 * representation of the board layout
 */
interface TileBoard {
    fun rowCount(): Int
    fun rowAt(n: Int): BoardRow
}

/**
 * GameBoard is the instantiation of a TileBoard for a specific game
 * - it logs the state - i.e. which tiles have been broken throughout the
 * course of the game, as well as exposing the TileBoard data.
 */
class GameBoard(b: TileBoard) : TileBoard by b {
    private val brokenRows = mutableSetOf<Int>()

    fun resetBroken() {
        brokenRows.clear()
    }

    fun breakRow(n: Int) {
        brokenRows.add(n)
    }

    fun isBrokenRow(n: Int) = brokenRows.contains(n)
}

class MutableTileBoard : TileBoard {
    private val board = mutableListOf<BoardRow>()

    override fun rowCount() = board.size
    override fun rowAt(n: Int) = board[n]

    fun addRow(row: BoardRow) = board.add(row)
}
