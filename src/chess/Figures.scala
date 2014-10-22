package chess


sealed abstract class Figures {
  def canMove(from: Pos, to: Pos): Boolean = true

  def diff(from: Pos, to: Pos) = (to - from).abs()
}

case object King extends Figures {
  override def toString: String = {
    "K"
  }

  override def canMove(from: Pos, to: Pos): Boolean = {
    val diff = super.diff(from, to)
    diff == Pos(0, 1) || diff == Pos(1, 0) || diff == Pos(1, 1)
  }
}

case object Rook extends Figures {
  override def toString: String = {
    "R"
  }

  override def canMove(from: Pos, to: Pos): Boolean = {
    val diff = super.diff(from, to)
    diff.x == 0 || diff.y == 0
  }
}


case object Bishop extends Figures {
  override def toString: String = {
    "B"
  }

  override def canMove(from: Pos, to: Pos): Boolean = {
    val diff = super.diff(from, to)
    diff.x == diff.y
  }
}

case object Queen extends Figures {
  override def toString: String = {
    "Q"
  }

  override def canMove(from: Pos, to: Pos): Boolean = {
    val diff = super.diff(from, to)
    diff.x == 0 || diff.y == 0 || diff.x == diff.y
  }
}

case object Knight extends Figures {
  override def toString: String = {
    "N"
  }

  override def canMove(from: Pos, to: Pos): Boolean = {
    val diff = super.diff(from, to)
    diff == Pos(1, 2) || diff == Pos(2, 1)
  }
}


