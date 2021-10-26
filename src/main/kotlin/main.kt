import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.int
import kotlin.math.floor

internal fun randomInt(max: Int) = floor(Math.random() * max).toInt() + 1

internal class App : CliktCommand() {

    private val players: Int by option("-p", "--players", help="number of players")
        .int().default(randomInt(16))
    private val iterations: Int by option("-n", "--iterations", help="number of iterations")
        .int().default(1000000)
    private val boardSize: Int by option("-s", "--board-size", help="size of random board if no config supplied")
        .int().default(randomInt(20))
    private val configPath by option("-c", "--config", help="path to config file (random board if empty)")
    private val output by option("-o", "--output", help="path to output file (stdout if empty)")

    override fun run() {
        val config = if (configPath != null) {
            println(">>> Reading config from file $configPath")
            Configurator.readConfigFromFile(configPath!!)
        } else {
            println(">>> Generating game board from supplied options..")
            Config(players, Simulation.generateRandomBoard(boardSize))
        }

        println("Board size:        ${config.board.rowCount()}")
        println("Number of players: ${config.numPlayers}")
        println("Iterations:        $iterations")

        println(">>> RUNNING...")
        val result = Simulation.simulate(config.board, config.numPlayers, iterations)

        if (output != null) {
            println(">>> Printing results to file: $output")
            Reporter.printSimulationResultsToFile(result, output!!)
        } else {
            println(">>> RESULTS:")
            Reporter.printSimulationResults(result)
        }

        println(">>> DONE <<<")
    }

}

fun main(args: Array<String>) {

    println("====================")
    println("TILE GAME SIMULATION")
    println("====================")
    println()

    App().main(args)
}