package rover

case class Rover(grid: Grid, x: Int, y: Int, facing: Char, lost: Boolean)

case class Grid(xDim: Int, yDim: Int)

object Rover {

  // Returns an updated state of the rover after 'move forward' command
  def moveForward(rover: Rover): Rover = {
    if (rover.lost) {
      // If LOST- don't change the state
      rover.copy()
    } else if (movingOutOfBounds(rover)) {
      // Change to 'lost' if heading out of bounds, position the same
      rover.copy(lost = true)
    } else {

      val (newX, newY) = rover.facing match {
        case 'N' => (rover.x, rover.y + 1)
        case 'E' => (rover.x + 1, rover.y)
        case 'S' => (rover.x, rover.y - 1)
        case 'W' => (rover.x - 1, rover.y)
      }
      rover.copy(x = newX, y = newY)
    }
  }

  // Returns whether the rover is to be position outside of the grid
  def isOutOfGrid(x: Int, y: Int, grid: Grid): Boolean = {
    x < 0 || x > grid.xDim || y < 0 || y > grid.yDim
  }

  // Returns an updated state of the rover after 'turn right' command
  def turnRight(rover: Rover): Rover = {
    // If LOST- don't change the state
    if (rover.lost) {
      rover.copy()
    } else {
      rover.facing match {
        case 'N' => rover.copy(facing = 'E')
        case 'E' => rover.copy(facing = 'S')
        case 'S' => rover.copy(facing = 'W')
        case 'W' => rover.copy(facing = 'N')
      }

    }
  }

  // Returns an updated state of the rover after 'turn left' command
  def turnLeft(rover: Rover): Rover = {
    // Turning 90deg left == turning 270deg right (+ less boilerplate)
    turnRight(turnRight(turnRight(rover)))
  }

  // Returns whether the rover would go into a 'LOST' position if moves forward
  def movingOutOfBounds(rover: Rover): Boolean = {
    ((rover.facing == 'N') && (rover.y == rover.grid.yDim)) ||
    ((rover.facing == 'E') && (rover.x == rover.grid.xDim)) ||
    ((rover.facing == 'S') && (rover.y == 0)) ||
    ((rover.facing == 'W') && (rover.x == 0))

  }

  // Updates the state of the rover with a command defined in inputs (as char)
  def executeCommand(rover: Rover, command: Char): Rover = {
    command match {
      case 'F' => moveForward(rover)
      case 'L' => turnLeft(rover)
      case 'R' => turnRight(rover)
    }
  }

}
