/**
 * Run a simulated game n times and add up the scores for each
 * player, averaged across all games
 */

data class SimulationResult(val playerChances: FloatArray, val failureChance: Float)

object Simulation {

    fun simulate(
        board: TileBoard,
        numPlayers: Int,
        numIterations: Int
    ) : SimulationResult {

        val playerSuccessCounts = IntArray(numPlayers) // automatically initialised to zero
        var failureCount = 0
        (0 until numIterations).forEach { _ ->
            val game = Game(board, numPlayers)
            val firstAcross = game.play()

            if (firstAcross < 0) {
                failureCount++
            } else {
                // remember if one player makes it, all subsequent players also make it
                for (i in (firstAcross until numPlayers)) {
                    playerSuccessCounts[i] ++
                }
            }
        }

        val playerChances = FloatArray(numPlayers) { i ->
            playerSuccessCounts[i].toFloat() / numIterations
        }
        val failureChance = failureCount.toFloat() / numIterations

        return SimulationResult(playerChances, failureChance)
    }

    fun generateRandomBoard(rows: Int): TileBoard {
        val board = MutableTileBoard()
        (0 until rows).forEach { _ ->
            board.addRow(if (Math.random() < 0.5) BoardRow.RightGood else BoardRow.LeftGood)
        }
        return board
    }
}