import java.util.*

/**
 * Class controlling an individual Game, which is a single run through
 * all players
 */
class Game(tileBoard: TileBoard, private val numPlayers: Int) {

    enum class Move { Left, Right }

    private val board = GameBoard(tileBoard)
    private val knownMoves = mutableListOf<Move>()
    private val finish = board.rowCount() - 1

    /**
     * Runs the game and returns the zero-indexed first player across the board
     * or -1 if none made it.
     */
    fun play(): Int {
        for (player in 0 until numPlayers) {
            var currentPosition = knownMoves.size
            if (currentPosition > finish) { // previous player failed on the final row
                // player wins
                return player
            }

            var move = randomMove()
            while( moveSuccessful(move, board.rowAt(currentPosition)) ) {
                knownMoves.add(move)
                currentPosition++
                if (currentPosition > finish) {
                    // player wins
                    return player
                }
                move = randomMove()
            }

            // player failed
//            board.breakRow(currentPosition) // not used in standard game, only variant
            knownMoves.add(oppositeMove(move))
        }

        // no-one made it!
        return -1
    }

    fun getKnownMoves(): List<Move> = Collections.unmodifiableList(knownMoves)

    private fun randomMove() =
        if (Math.random() < 0.5) Move.Left else Move.Right

    private fun moveSuccessful(move: Move, row: BoardRow): Boolean {
        return (move == Move.Left && row == BoardRow.LeftGood) ||
                (move == Move.Right && row == BoardRow.RightGood)
    }

    private fun oppositeMove(move: Move) =
        if (move == Move.Left) Move.Right else Move.Left
}