chess-game
==========

Chess game solver

USAGE:

Basically main usage is singleton Solver which has params:

    Solver(figures: List[Figures], _maxX: Int, _maxY: Int):List[Solution]

For example `Solver(List(King, King, Rook), 3, 3)` will find the solutions for 2 Kings and 1 Rook on 3x3 board
The result will be something like :


    List(
        Map(Pos(0, 0) -> King, Pos(2, 0) -> King, Pos(1, 2) -> Rook),
        Map(Pos(0, 0) -> King, Pos(0, 2) -> King, Pos(2, 1) -> Rook),
        Map(Pos(2, 0) -> King, Pos(2, 2) -> King, Pos(0, 1) -> Rook),
        Map(Pos(0, 2) -> King, Pos(2, 2) -> King, Pos(1, 0) -> Rook)
    )


To get the number of results just use `Solver(List(King, King, Rook), 3, 3).size` (will return you 4).

There is a function for graphics output called `Solver.printOutput`.

    printOutput(solutionsList: List[Solution])(implicit limit: Int): Unit

limit is the number of solutions printed out graphically in console. 
For example `Solver.printOutput(Solver(List(King, King, Rook), 3, 3))(2)` will output : 

Start

Solutions:4

    ┌----------┐
    | K |  |K  |
    |----------|
    |   |  |   |
    |----------|
    |   |R |   |
    └----------┘
    ┌----------┐
    | K |  |   |
    |----------|
    |   |  |R  |
    |----------|
    | K |  |   |
    └----------┘

CONFIGURATION:

JAVA_OPT = "-server -Xss1024m -XX:MaxPermSize=1900m -Xms2g -Xmx2g"
