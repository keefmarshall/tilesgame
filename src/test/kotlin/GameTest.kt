import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class GameTest {

    private fun buildTestBoard(): GameBoard {
        val board = MutableTileBoard()
        board.addRow(BoardRow.LeftGood)
        board.addRow(BoardRow.RightGood)
        return GameBoard(board)
    }

    @BeforeEach
    fun setUp() {
    }

    @Test
    fun play() {
        val board = buildTestBoard()
        // Going to run this 10 times to test every outcome
        (0 until 10).forEach { _ ->
            val game = Game(board, 2)
            val firstAcross = game.play()
            // Difficult to test a random function, but it should be within defined params:
            assertTrue(firstAcross == 0 || firstAcross == 1 || firstAcross == -1)
            assertEquals(2, game.getKnownMoves().size) // should always be two moves
        }
    }
}

