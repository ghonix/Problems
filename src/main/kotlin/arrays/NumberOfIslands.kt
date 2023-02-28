package arrays

import java.util.LinkedList
import java.util.Queue


class NumberOfIslands {

    data class Coordinate(val row: Int, val col: Int)

    fun numIslands(grid: Array<CharArray>): Int {
        val visited = HashSet<Coordinate>()
        var islandsFound = 0
        for (row in grid.indices) {
            for (col in grid[row].indices) {
                val current = grid[row][col]
                if (current == '0' || visited.contains(Coordinate(row, col))) {
                    continue
                }
                exploreIterative(grid, row, col, visited)
                islandsFound++
            }
        }
        return islandsFound
    }

    private fun exploreRecursive(grid: Array<CharArray>, row: Int, col: Int, visited: java.util.HashSet<Coordinate>) {
        if (visited.contains(Coordinate(row, col))) {
            return
        }
        visited.add(Coordinate(row, col))
        val dr = arrayOf(0, 1, 0, -1)
        val dc = arrayOf(1, 0, -1, 0)
        for (i in dr.indices) {
            val newRow = row + dr[i]
            val newCol = col + dc[i]
            if (isValid(grid, newRow, newCol)) {
                exploreRecursive(grid, newRow, newCol, visited)
            }
        }
    }

    private fun exploreIterative(grid: Array<CharArray>, row: Int, col: Int, visited: java.util.HashSet<Coordinate>) {
        val queue: Queue<Coordinate> = LinkedList()
        queue.add(Coordinate(row, col))
        while (queue.isNotEmpty()) {
            val current = queue.poll()
            if (visited.contains(current)) {
                continue
            }
            visited.add(current)
            val dr = arrayOf(0, 1, 0, -1)
            val dc = arrayOf(1, 0, -1, 0)
            for (i in dr.indices) {
                val newRow = current.row + dr[i]
                val newCol = current.col + dc[i]
                if (isValid(grid, newRow, newCol)) {
                    queue.add(Coordinate(newRow, newCol))
                }
            }
        }
    }

    private fun isValid(grid: Array<CharArray>, row: Int, col: Int): Boolean {
        return row >= 0 && row < grid.size && col >= 0 && col < grid[row].size && grid[row][col] == '1'
    }
}


fun main() {
    println(
        NumberOfIslands().numIslands(
            arrayOf(
                charArrayOf('1', '1', '0', '0', '0'),
                charArrayOf('1', '1', '0', '0', '0'),
                charArrayOf('0', '0', '1', '0', '0'),
                charArrayOf('0', '0', '0', '1', '1')
            )
        )
    )
}