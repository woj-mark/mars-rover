package rover

object Main extends App {

  // Reads the inputs from a text file located in the "inputs" directory
  val input: List[String] =
    scala.io.Source.fromFile("inputs/inputs1.txt").getLines.toList

  // Defines the grid from the inputs
  val gridInputs = input.head.split(" ")
  val grid: Grid = gridInputs.toList match {
    case List(xdim, ydim) => Grid(xdim.toInt, ydim.toInt)
  }

  // The resultant rovers for provided inputs
  val finalRovers: List[Rover] = returnFinalRovers(input.tail)

  // The final state of the rovers returned in the console
  println(finalRovers)

  // Function calculating the final rover an input line string (i.e."(2, 3, N) FLLFR")
  def computeFinalRover(line: String): Rover = {

    // Extracts rover position and commands from inputs
    val roverInputs = CommandParser.parseInitialRover(line)
    val roverCommands = CommandParser.parseRoverCommands(line)

    val isOutOfGrid: Boolean =
      Rover.isOutOfGrid(roverInputs(0).asDigit, roverInputs(1).asDigit, grid)

    // Initialises the rover
    val rover: Rover =
      Rover(
        grid,
        roverInputs(0).asDigit,
        roverInputs(1).asDigit,
        roverInputs(2),
        isOutOfGrid
      )

    // Iterates over the command arrray and returns the state of the rover accordingly
    val finalRover = roverCommands.foldLeft(rover) { (currentRover, command) =>
      Rover.executeCommand(currentRover, command)
    }
    finalRover
  }

  // Iterates over the lines rover inputs (positions + commands) and returns the final state of each rover
  def returnFinalRovers(input: List[String]): List[Rover] = {
    input.map(line => computeFinalRover(line))
  }

}
