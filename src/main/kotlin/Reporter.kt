import java.io.File
import java.io.PrintStream

object Reporter {
    fun printSimulationResults(results: SimulationResult, out: PrintStream = System.out) {
        out.printf("%d,%f\n", 0, results.failureChance)
        (0 until results.playerChances.size).forEach { player ->
            out.printf("%d,%f\n", player + 1, results.playerChances[player])
        }
    }

    fun printSimulationResultsToFile(results: SimulationResult, filePath: String) {
        val out = PrintStream(File(filePath).outputStream())
        printSimulationResults(results, out)
    }
}