## Mars Rover- take home challenge

### Overview
This repository contains my solution to the Mars Rover problem using Scala.
I have attempted the problem using Scala and functional programming. I adopted Test Driven Development using ScalaTest package and used immutable data structures, algebraic data types (ADTs) and pure functions to model the behaviour of the rover and parse the inputs. In my opinion, this makes reasoning about code easier and simplifies testing.

### How to run the program
It is assumed that the user has sbt installed on their machine. If not, please refer to the following [link](https://www.scala-sbt.org/1.x/docs/Setup.html) to learn how to install it.

Please navigate to the root directory of the project in the Command Prompt and execute: `sbt run`. The program will print out a list of the final states of the Mars Rover(s) in the console in the following format: `List(Rover(Grid(4,8),4,4,E,false)`. 

The programme calculates the final state of the rover for the text input provided in .txt files in the 'Inputs' directory. The required format of the .txt file is as provided in the documentation.

### What I would improve
Over the suggested 3 hours slot for the task, I prioritised implementation of the logic using pure functions and TDD which would enable me to simulate the final state of the Mars Rover(s) in accordance with the documentation.

The solution simulates the correct position, orientation (and whether lost or not), but I didn't manage to implement the functionality to parse the Mars Rover object to strings as specified in the project sheet. If I had more time, I would add a function which would parse the final Mars Rover objects into strings printed in the console which would be more user-friendly. I would also add a functionality to allow the user to provide the inputs in the console.

If I had more time, I would make the programme more robust against illegal user inputs (i.e. nulls, negative integers etc.), spot errors and propagate them. I am aware that there is currently a limited amount of validation of user inputs other than those provided in the documentation. 
