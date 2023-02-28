package arrays

import java.lang.Integer.min

class MinimumDistance {

    fun minDistance(word1: String, word2: String): Int {
        val len1 = word1.length
        val len2 = word2.length

        val memory = Array(len1 + 1) { IntArray(len2 + 1) }

        for (i in memory.indices) {
            memory[i][0] = i
        }

        for (j in memory[0].indices) {
            memory[0][j] = j
        }

        for (row in 1..len1) {
            for (col in 1..len2) {
                if (word1[row - 1] == word2[col - 1]) {
                    memory[row][col] = memory[row - 1][col - 1]
                } else {
                    memory[row][col] = 1 + min(
                        memory[row - 1][col - 1],
                        min(
                            memory[row - 1][col],
                            memory[row][col - 1]
                        )
                    )
                }
            }
        }
        return memory[len1][len2]
    }
}

fun main() {
    println(
        MinimumDistance().minDistance("horse", "ros")
    )
    println(
        MinimumDistance().minDistance("intention", "execution")
    )
}