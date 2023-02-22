package rover
import org.scalatest._
import org.scalatest.funsuite.AnyFunSuite

class CommandParserSuite extends AnyFunSuite {

  val input1 = "(2, 3, E) LFRFF"
  val input2 = "(0, 2, N) FFLFRFF"
  val input3 = "(2, 3, N) FLLFR"
  val input4 = "(1, 0, S) FFRLF"

  test(
    "Parsing input lines into rover variables should be correct"
  ) {
    assert(
      CommandParser.parseInitialRover(input1) === List('2', '3', 'E'),
      "Parsed rover inputs into a list of char incorrectly"
    )
    assert(
      CommandParser.parseInitialRover(input2) === List('0', '2', 'N'),
      "Parsed rover inputs into a list of char incorrectly"
    )
    assert(
      CommandParser.parseInitialRover(input3) === List('2', '3', 'N'),
      "Parsed rover inputs into a list of char incorrectly"
    )
    assert(
      CommandParser.parseInitialRover(input4) === List('1', '0', 'S'),
      "Parsed rover inputs into a list of char incorrectly"
    )
  }

  test(
    "Parsing input lines into rover commands List[Char] is correct"
  ) {
    assert(
      CommandParser.parseRoverCommands(input1) === List('L', 'F', 'R', 'F',
        'F'),
      "Parsed command inputs into a list of char incorrectly"
    )
    assert(
      CommandParser.parseRoverCommands(input2) === List('F', 'F', 'L', 'F', 'R',
        'F', 'F'),
      "Parsed command inputs into a list of char incorrectly"
    )
    assert(
      CommandParser.parseRoverCommands(input3) === List('F', 'L', 'L', 'F',
        'R'),
      "Parsed command inputs into a list of char incorrectly"
    )
    assert(
      CommandParser.parseRoverCommands(input4) === List('F', 'F', 'R', 'L',
        'F'),
      "Parsed command inputs into a list of char incorrectly"
    )
  }

}
