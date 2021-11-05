object TilesGameVariant {

  // Returns zero-indexed array of booleans of size numPlayers
  // - index 0 is true if player 1 made it, and so on
  def runGame(boardSize: Int = 2, numPlayers: Int = 2) = {
    var currentPosition = 0
    var currentPlayer = 1

    val boardState = new Array[Boolean](boardSize) // false is unknown, true is known

    // need to find the each successful player individually
    for (_ <- 0 until numPlayers) yield {
      var position = 0
      var alive = true
      while (position < boardSize && alive) {
        if (boardState(position) || Math.random() >= 0.5) {
          // player succeeds
          position += 1
        } else {
          // tile broke, player failed
          alive = false
          boardState(position) = true
        }
      }

      alive // return true/false for failed/succeeded
    }
  }

  def runSimulation(boardSize: Int = 2, numPlayers: Int = 2, iterations: Int = 1000000) = {
    // index 0 is no players getting across, first player is index 1
    val playerSuccesses = new Array[Int](numPlayers + 1)
    for (_ <- 1 to iterations) {
      val gameResults = runGame(boardSize, numPlayers)
      if (!gameResults.contains(true)) playerSuccesses(0) += 1 // no-one won
      else for (p <- 1 to numPlayers if gameResults(p - 1)) {
        // This player won
        playerSuccesses(p) += 1
      }
    }

    for (s <- playerSuccesses) yield (s: Double) / iterations
  }

  def main(args: Array[String]): Unit = {
    val boardSize = if (args.length >= 1) args(0).toInt else 2
    val numPlayers = if (args.length >= 2) args(1).toInt else 2
    val iterations = if (args.length >= 3) args(2).toInt else 1000000
    val result = runSimulation(boardSize, numPlayers, iterations)
    for (p <- result.indices) println(s"$p,${result(p)}")
  }

}