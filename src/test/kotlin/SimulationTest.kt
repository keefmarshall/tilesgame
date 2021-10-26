import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class SimulationTest {

    private fun buildTestBoard(): TileBoard {
        val board = MutableTileBoard()
        board.addRow(BoardRow.LeftGood)
        board.addRow(BoardRow.RightGood)
        return board
    }

    @Test
    fun simulate() {
        val result = Simulation.simulate(buildTestBoard(), 2, 100000)
        println("Result was $result")
        assertEquals(2, result.playerChances.size)
        val delta = 0.005f
        assertEquals(0.25f, result.failureChance, delta)
        assertEquals(0.25f, result.playerChances[0], delta)
        assertEquals(0.75f, result.playerChances[1], delta)
    }

    @Test
    fun simulate3() {
        val board = Simulation.generateRandomBoard(3)
        val result = Simulation.simulate(board, 3, 1000000)
        println("Result was $result")
        assertEquals(3, result.playerChances.size)
        val delta = 0.001f
        assertEquals(0.125f, result.failureChance, delta)
        assertEquals(0.125f, result.playerChances[0], delta)
        assertEquals(0.50f, result.playerChances[1], delta)
        assertEquals(0.875f, result.playerChances[2], delta)
    }

    @Test
    fun simulateSG() {
        val board = Simulation.generateRandomBoard(18)
        val result = Simulation.simulate(board, 16, 10000000)
        println("Result was $result")
        assertEquals(16, result.playerChances.size)
    }
}