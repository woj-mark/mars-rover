package rover

object CommandParser {

  // Helper function to parse the rover input position
  def parseInitialRover(inputLine: String): List[Char] = {
    val roverInputs = inputLine.splitAt(9)
    val roverIntialList =
      roverInputs._1.filter(c => c.isDigit || c.isLetter).toList
    roverIntialList
  }

  // Helper function to parse the rover input commands
  def parseRoverCommands(inputLine: String): List[Char] = {
    val roverInputs = inputLine.splitAt(9)
    val roverCommands = roverInputs._2.filter(c => c.isLetter).toList
    roverCommands
  }
}
