import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream

internal class ReporterTest {

    private fun buildSimulationResult(): SimulationResult {
        val playerChances = arrayOf(0.25f, 0.75f)
        return SimulationResult(playerChances.toFloatArray(), 0.25f)
    }

    private val expectedOutput = "0,0.250000\n1,0.250000\n2,0.750000\n"

    @Test
    fun printSimulationResults() {
        val outBase = ByteArrayOutputStream()
        val out = PrintStream(outBase)
        Reporter.printSimulationResults(buildSimulationResult(), out)
        val outputString = String(outBase.toByteArray())
        assertEquals(expectedOutput, outputString)
    }

    @Test
    fun printSimulationResultsToFile() {
        val tempFile = File.createTempFile("tileGameTestOutput", "csv")
        tempFile.deleteOnExit()
        Reporter.printSimulationResultsToFile(buildSimulationResult(), tempFile.absolutePath)
        assertEquals(expectedOutput, tempFile.readText())
    }
}