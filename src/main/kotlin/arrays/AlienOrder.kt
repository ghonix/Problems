package arrays

import java.lang.StringBuilder
import java.util.LinkedList
import java.util.Queue

class AlienOrder {

    fun alienOrder(words: Array<String>): String {
        val adjList = HashMap<Char, ArrayList<Char>>()
        val counts = HashMap<Char, Int>()

        for (word in words) {
            for (c in word) {
                if (adjList[c] == null) {
                    adjList[c] = ArrayList()
                }
                counts[c] = 0
            }
        }

        // get as much information as possible about ordering of elements.
        for (i in 0..words.size - 2) {
            val word1 = words[i]
            val word2 = words[i + 1]

            if (word1.length > word2.length && word1.startsWith(word2)) {
                return ""
            }

            for (j in 0 until Math.min(word1.length, word2.length)) {
                if (word1[j] != word2[j]) {
                    adjList[word1[j]]?.add(word2[j])
                    counts[word2[j]] = counts[word2[j]]!! + 1
                    break
                }
            }
        }

        // Do a sort of topological sort to get the order of chars in the alphabet
        val sb = StringBuilder()
        val queue: Queue<Char> = LinkedList()
        // Add all nodes that has no incoming edges
        for (c in counts.keys) {
            if (counts[c] == 0) {
                queue.add(c)
            }
        }

        while (queue.isNotEmpty()) {
            val current = queue.poll()

            sb.append(current)
            val neighbors = adjList[current]
            neighbors?.let {
                for (neighbor in neighbors) {
                    counts[neighbor] = counts[neighbor]!! - 1
                    if (counts[neighbor] == 0) {
                        queue.add(neighbor)
                    }
                }
            }
        }
        if (sb.length < counts.size) {
            return ""
        }

        return sb.toString()
    }
}

fun main() {
    println(
        AlienOrder().alienOrder(arrayOf("wrt", "wrf", "er", "ett", "rftt"))
    )
}