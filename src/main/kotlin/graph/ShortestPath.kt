package graph

import graph.Graph.Companion.createAdjacencyList
import java.util.PriorityQueue

class ShortestPath {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            ShortestPath().shortestPath()
        }
    }

    data class PathData(
        val node: String,
        var cost: Int,
        var dst: String)

    private fun shortestPath() {
        val graph = HashMap<String, Set<String>>()
        graph["1"] = setOf("2")
        graph["2"] = setOf("1")
        graph["3"] = emptySet()
        graph["4"] = setOf("6")
        graph["5"] = setOf("6")
        graph["6"] = setOf("4", "5", "7", "8")
        graph["7"] = setOf("6")
        graph["8"] = setOf("6")

        shortestPath("w", "z")
    }

    private fun shortestPath(src: String, dst: String) {
        val priorityQueue = PriorityQueue<PathData>(compareBy { it.cost })

        while (priorityQueue.isNotEmpty()) {
            println(priorityQueue.poll().cost)
        }
    }
}