object TilesGame {

  def runGame(boardSize: Int = 2, numPlayers: Int = 2) = {
    var currentPosition = 0
    var currentPlayer = 1

    // need to find the first successful player
    while (currentPosition < boardSize && currentPlayer <= numPlayers) {
      currentPosition += 1
      if (Math.random() < 0.5) currentPlayer += 1
    }

    if (currentPlayer > numPlayers) 0 else currentPlayer
  }

  def runSimulation(boardSize: Int = 2, numPlayers: Int = 2, iterations: Int = 1000000) = {
    // index 0 is no players getting across, first player is index 1
    val playerSuccesses = new Array[Int](numPlayers + 1)
    for (_ <- 1 to iterations) {
      val firstPlayer = runGame(boardSize, numPlayers)
      if (firstPlayer == 0) playerSuccesses(0) += 1
      else for (p <- firstPlayer to numPlayers) {
        // all subsequent players succeed in original version
        playerSuccesses(p) += 1
      }
    }

    for (s <- playerSuccesses) yield (s:Double) / iterations
  }

  def main(args: Array[String]): Unit = {
    val boardSize = if (args.length >= 1) args(0).toInt else 2
    val numPlayers = if (args.length >= 2) args(1).toInt else 2
    val iterations = if (args.length >= 3) args(2).toInt else 1000000
    val result = runSimulation(boardSize, numPlayers, iterations)
    for (p <- result.indices) println(s"$p,${result(p)}")
  }
}