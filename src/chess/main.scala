package chess

import chess.main.Solution

case class Pos(x: Int, y: Int) {
  def +(other: Pos): Pos = Pos(x + other.x, y + other.y)

  def -(other: Pos): Pos = Pos(x - other.x, y - other.y)

  def abs() = Pos(Math.abs(x), Math.abs(y))
}

class Solver(private val figures: List[Figures], private val m: Int, private val n: Int) {

  def solve(): List[Solution] = {
    def solveAcc(state: Solution, figures: List[Figures] = List(), availablePositions: List[Pos]): List[Solution] = {
      figures match {
        case List() =>
          List(state)
        case fig :: otherFig => val next = for {
          pos <- availablePositions

          if state.forall { case (oPos, oFig) => !fig.canMove(pos, oPos)}
          nextState: Solution = state + (pos -> fig)
          newAvailablePositions = availablePositions.filter {
            oPos => !fig.canMove(oPos, pos) && oPos != pos
          }
          nextSolution <- solveAcc(nextState, otherFig, newAvailablePositions)
        } yield nextSolution
          next.distinct
      }
    }
    val availablePositions = (for {
      y <- 0 until n
      x <- 0 until m
    } yield Pos(x, y)).toList

    solveAcc(Map(), figures, availablePositions)
  }

  def dimensions = (m, n)

}


object Solver {
  var n: Int = 8
  var m: Int = 8

  def apply(figures: List[Figures], _m: Int, _n: Int) = {
    n = _n
    m = _m
    new Solver(figures, m, n).solve()
  }

  def printOutput(solutionsList: List[Solution])(implicit limit: Int): Unit = {
    val list = if (limit > 0) {
      solutionsList.take(limit)
    } else {
      solutionsList
    }

    println("Solutions:" + solutionsList.size.toLong)
    for (solution <- list) {
      val cover = ("---" * m) + "-"
      val limitX = m - 1
      val limitY = n - 1
      println("┌" + cover + "┐")

      for {
        y <- 0 to limitY
        x <- 0 to limitX
      } {
        if (x == 0) print("| ")
        print(solution.getOrElse(Pos(x, y), " ") + " ")
        print(if (x != limitX) "|" else " |\n")
        if (x == limitX && y != limitY) print("|" + cover + "|\n")
      }
      println("└" + cover + "┘")
    }
  }
}


object main extends App {
  implicit val limit: Int = 0
  type Solution = Map[Pos, Figures]
  println("Start")
  println("2 Kings and 1 Rook on 3x3 board : ")
  print(Solver(List(King, King, Rook), 3, 3) == List(
    Map(Pos(0, 0) -> King, Pos(2, 0) -> King, Pos(1, 2) -> Rook),
    Map(Pos(0, 0) -> King, Pos(0, 2) -> King, Pos(2, 1) -> Rook),
    Map(Pos(2, 0) -> King, Pos(2, 2) -> King, Pos(0, 1) -> Rook),
    Map(Pos(0, 2) -> King, Pos(2, 2) -> King, Pos(1, 0) -> Rook)))
  println("\n8 Queens on 8x8 board (92 solutions): ")
  print(Solver(List(Queen, Queen, Queen, Queen, Queen, Queen, Queen, Queen), 8, 8).size == 92)
  println("\n7×7 board with 2 Kings, 2 Queens, 2 Bishops and 1 Knight (3063828 solutions): ")
  print(Solver(List(Queen, Queen, King, King, Bishop, Bishop, Knight), 7, 7).size == 3063828)
  println("\n4×4 board containing 2 Rooks and 4 Knights (graphics output): ")
  Solver.printOutput(Solver(List(Rook, Rook, Knight, Knight, Knight, Knight), 4, 4))
  println("\nEnd.")
}
