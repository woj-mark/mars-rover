package rover
import org.scalatest._
import org.scalatest.funsuite.AnyFunSuite

class RoverSuite extends AnyFunSuite {

  val grid25 = Grid(25, 25)

  val northFacingRover = Rover(grid25, 13, 12, 'N', false)
  val eastFacingRover = Rover(grid25, 13, 12, 'E', false)
  val southFacingRover = Rover(grid25, 13, 12, 'S', false)
  val westFacingRover = Rover(grid25, 13, 12, 'W', false)

  val roverAtBoundaryN = Rover(grid25, 25, 25, 'N', false)
  val roverAtBoundaryS = Rover(grid25, 0, 0, 'S', false)

  val lostRover = Rover(grid25, 0, 0, 'S', true)

  test(
    "moveForward should change the position of the rover correctly within bounds"
  ) {
    assert(
      Rover.moveForward(northFacingRover) === Rover(grid25, 13, 13, 'N', false),
      "moved forward North incorrectly"
    )
    assert(
      Rover.moveForward(eastFacingRover) === Rover(grid25, 14, 12, 'E', false),
      "moved forward East incorrectly"
    )
    assert(
      Rover.moveForward(southFacingRover) === Rover(grid25, 13, 11, 'S', false),
      "moved forward South incorrectly"
    )
    assert(
      Rover.moveForward(westFacingRover) === Rover(grid25, 12, 12, 'W', false),
      "moved forward West incorrectly"
    )
  }

  test(
    "moveForward should mark rover as lost if heading out of the grid"
  ) {
    assert(
      Rover.moveForward(roverAtBoundaryN) === Rover(grid25, 25, 25, 'N', true),
      "Not detected switching to lost."
    )
    assert(
      Rover.moveForward(roverAtBoundaryS) === Rover(grid25, 0, 0, 'S', true),
      "Not detected switching to lost."
    )
  }

  test(
    "Commands should return a lost rover with the same position"
  ) {
    assert(
      Rover.moveForward(lostRover) === Rover(grid25, 0, 0, 'S', true),
      "Moving a lost rover (incorrect)"
    )
    assert(
      Rover.turnLeft(lostRover) === Rover(grid25, 0, 0, 'S', true),
      "Turning a lost rover left (incorrect)."
    )

    assert(
      Rover.turnRight(lostRover) === Rover(grid25, 0, 0, 'S', true),
      "Turning a lost rover right (incorrect)."
    )
  }

  test("testing isOutOfGrid function") {
    assert(Rover.isOutOfGrid(26, 26, grid25) == true)
    assert(Rover.isOutOfGrid(100, 26, grid25) == true)
    assert(Rover.isOutOfGrid(0, 0, grid25) == false)
    assert(Rover.isOutOfGrid(25, 25, grid25) == false)
  }

  test("turnRight should rotate the rover correctly") {
    assert(
      Rover.turnRight(northFacingRover) === Rover(grid25, 13, 12, 'E', false),
      "North-facing turned right incorrectly"
    )
    assert(
      Rover.turnRight(eastFacingRover) === Rover(grid25, 13, 12, 'S', false),
      "East-facing turned right incorrectly"
    )
    assert(
      Rover.turnRight(southFacingRover) === Rover(grid25, 13, 12, 'W', false),
      "South-facing turned right incorrectly"
    )
    assert(
      Rover.turnRight(westFacingRover) === Rover(grid25, 13, 12, 'N', false),
      "West-facing turned right incorrectly"
    )
  }

  test("turnLeft should rotate the rover correctly") {
    assert(
      Rover.turnLeft(northFacingRover) === Rover(grid25, 13, 12, 'W', false),
      "North-facing turned left incorrectly"
    )
    assert(
      Rover.turnLeft(eastFacingRover) === Rover(grid25, 13, 12, 'N', false),
      "East-facing turned left incorrectly"
    )
    assert(
      Rover.turnLeft(southFacingRover) === Rover(grid25, 13, 12, 'E', false),
      "South-facing turned left incorrectly"
    )
    assert(
      Rover.turnLeft(westFacingRover) === Rover(grid25, 13, 12, 'S', false),
      "West-facing turned left incorrectly"
    )
  }

  test("movingOutOfBounds should return a correct boolean ") {
    assert(Rover.movingOutOfBounds(Rover(grid25, 25, 25, 'N', false)) === true)
    assert(Rover.movingOutOfBounds(Rover(grid25, 25, 25, 'E', false)) === true)
    assert(Rover.movingOutOfBounds(Rover(grid25, 0, 0, 'W', false)) === true)
    assert(Rover.movingOutOfBounds(Rover(grid25, 0, 0, 'S', false)) === true)
    assert(Rover.movingOutOfBounds(Rover(grid25, 25, 25, 'S', false)) === false)
    assert(Rover.movingOutOfBounds(Rover(grid25, 25, 25, 'W', false)) === false)
    assert(Rover.movingOutOfBounds(Rover(grid25, 0, 0, 'N', false)) === false)
    assert(Rover.movingOutOfBounds(Rover(grid25, 0, 0, 'E', false)) === false)
  }
  test("executeCommand should transform the state of the rover accordingly") {
    assert(
      Rover
        .executeCommand(northFacingRover, 'F') === Rover(
        grid25,
        13,
        13,
        'N',
        false
      ),
      "command to move forward executed incorrectly"
    )
    assert(
      Rover
        .executeCommand(northFacingRover, 'R') === Rover(
        grid25,
        13,
        12,
        'E',
        false
      ),
      "command to turn right executed incorrectly"
    )

    assert(
      Rover
        .executeCommand(northFacingRover, 'L') === Rover(
        grid25,
        13,
        12,
        'W',
        false
      ),
      "command to turn left executed incorrectly"
    )
  }
}
