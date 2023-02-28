package arrays

import java.util.Vector

class TicTacToe(val n: Int) {

    val board = Array<IntArray>(n) {
        IntArray(n) { -1 }
    }
    val movesMap = HashMap<Int, Int>()

    fun move(row: Int, col: Int, player: Int): Int {
        if (row < 0 || col < 0 || row > n || col > n) {
            return 0
        }

        board[row][col] = player

        incrementMoves(player)
        if (movesMap[player]!! < n) {
            return 0
        }
        val directions = Vector<Pair<Int, Int>>()
        if (row == col) {
            // check diagonal win
            directions.add(Pair(1, 1))
        }
        if (row == n - col - 1) {
            directions.add(Pair(-1, 1))
        }
        directions.add(Pair(0, 1))
        directions.add(Pair(1, 0))

        return checkForWin(player, row, col, directions)
    }

    private fun incrementMoves(player: Int) {
        movesMap[player] = movesMap[player]?.inc() ?: 1
    }

    private fun checkForWin(player: Int, row: Int, col: Int, directions: Vector<Pair<Int, Int>>): Int {
        for (direction in directions) {
            // go in one direction till end while counting
            var countInRow = 0
            var r = row
            var c = col
            var dr = direction.first
            var dc = direction.second
            while (r < n && c < n && board[r][c] == player) {
                countInRow++
                r += dr
                c += dc
            }

            // reverse
            dr *= -1
            dc *= -1
            r = row + dr
            c = col + dc

            while (r >= 0 && c >= 0 && board[r][c] == player) {
                countInRow++
                r += dr
                c += dc
            }
            if (countInRow >= n) {
                return player
            }
        }
        return 0
    }


}

fun main() {
    val game = TicTacToe(4)
    println(game.move(0, 3, 1))
    println(game.move(3, 3, 2))
    println(game.move(3, 0, 1))
    println(game.move(0, 0, 2))
    println(game.move(2, 1, 1))
    println(game.move(2, 2, 2))
    println(game.move(1, 2, 1))
}