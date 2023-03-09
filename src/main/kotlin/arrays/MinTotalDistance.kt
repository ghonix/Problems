package arrays

class MinTotalDistance {
    fun minTotalDistance(grid: Array<IntArray>): Int {
        var minDistance = 0
        val rows = ArrayList<Int>()
        val cols = ArrayList<Int>()

        for (i in grid.indices) {
            for (j in grid[i].indices) {
                if (grid[i][j] == 1) {
                    rows.add(i)
                    cols.add(j)
                }
            }
        }

        // rows is sorted already. so we can only sort cols
        cols.sort()

        return minDistance(rows) + minDistance(cols)
    }

    fun minDistance(list: ArrayList<Int>): Int {
        var i = 0
        var j = list.size - 1
        var distance = 0
        while (i < j) {
            distance += (list[j] - list[i])
            j--
            i++
        }

        return distance
    }
}

fun main() {
    println(
        MinTotalDistance().minTotalDistance(
            arrayOf(
                intArrayOf(1, 0, 0, 0, 1),
                intArrayOf(0, 0, 0, 0, 0),
                intArrayOf(0, 0, 1, 0, 0)
            )
        )
    )
}