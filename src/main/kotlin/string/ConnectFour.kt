package string

import java.util.HashMap

class ConnectFour {

    /*
     Connection board game

     Connection board game is two-player game, in which the players choose a color and then take turns dropping colored discs into a unlimited-column, unlimited-row vertically suspended grid. Each player chooses a color (blue or red) and takes turns dropping colored discs into the grid. The pieces fall straight down, occupying the lowest available space within the column.

    1. Player 1 choose "Red"
    2. Player 2 choose "Blue"
    3. Player 1 put a disc at position 0 put('R', 0)
    4. Player 2 put a disc at position 1 put('B', 1)
    5. Player 1 put a disc at position 1 put('R', 0)


                      R. <-- win!
                      R
                      R
                   R  B. B.    B
          -  -  -  -  -  -  -  -  -
    ...  -3 -2 -1  0  1  2  3  4  5  ...

    Task 1:
    The user wins while there are three connected discs of the same color in a column.

    Task 2:
    The user also wins while there are three connected discs of the same color in a row as well.

    boolean put(char color, int position) // indicating if the player has won or not after this step


    var columns = Hash<Int, Vector<Char>>
    */


    private val columns = HashMap<Int, MutableList<Char>>()

    fun put(color: Char, position: Int): Boolean {
        var column = columns[position]
        if (column == null) {
            column = mutableListOf<Char>()
            column.add(color)
            columns[position] = column
        } else {
            column.add(color)
        }
        val wonInColumn = validateColumn(color, column)
        if (wonInColumn) {
            return true
        }

        return validateRow(columns, position, color, 3)
    }

    private fun validateRow(columns: HashMap<Int, MutableList<Char>>, position: Int, color: Char, size: Int): Boolean {
        val currentRow = columns[position]?.let { it.size - 1 } ?: 0
        return if (currentRow < 0) {
            false
        } else {
            checkNeighbors(columns, position, currentRow, color, size)
        }
    }

    private fun checkNeighbors(
        columns: HashMap<Int, MutableList<Char>>,
        position: Int,
        currentRow: Int,
        color: Char,
        size: Int
    ): Boolean {
        var howManyInRow = 0
        var col = position
        while (howManyInRow < size) {
            if (checkColumn(columns[col], currentRow, color)) {
                col++
                howManyInRow++
            } else {
                break
            }
        }
        col = position - 1
        while (howManyInRow < size) {
            if (checkColumn(columns[col], currentRow, color)) {
                col--
                howManyInRow++
            } else {
                break
            }
        }
        return howManyInRow >= size
    }

    private fun checkColumn(column: MutableList<Char>?, currentRow: Int, color: Char): Boolean {
        return if (column == null) {
            false
        } else if (column.size - 1 < currentRow) {
            false
        } else {
            column[currentRow] == color
        }
    }


    private fun validateColumn(color: Char, column: MutableList<Char>): Boolean {
        if (column.size >= 3) {
            val itr = column.listIterator(column.size)
            var i = 0
            // `hasPrevious()` returns true if the list has a previous element
            while (itr.hasPrevious() && i < 2) {
                val c = itr.previous()
                if (c != color) {
                    return false
                }
                i++
            }
            return true
        } else {
            return false
        }
    }
}

fun main(args: Array<String>) {

    val game = ConnectFour()
    println(game.put('R', 0))
    println(game.put('R', 1))
    println(game.put('C', 1))
    println(game.put('R', 2))


}