package graph

import java.lang.Integer.max
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class LargestConnectedComponent {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(LargestConnectedComponent().largestConnectedComponent())
        }
    }

    public fun largestConnectedComponent(): Int {
        val graph = HashMap<String, Set<String>>()
        graph["1"] = setOf("2")
        graph["2"] = setOf("1")
        graph["3"] = emptySet()
        graph["4"] = setOf("6")
        graph["5"] = setOf("6")
        graph["6"] = setOf("4", "5", "7", "8")
        graph["7"] = setOf("6")
        graph["8"] = setOf("6")

        return largestConnectedComponent(graph)
    }

    private fun largestConnectedComponent(
        graph: HashMap<String, Set<String>>
    ): Int {
        val visited = HashSet<String>()
        var largest = 0
        for (current in graph.keys) {
            if (!visited.contains(current)) {
                largest = max(largest, explore(graph, current, visited))
            }
        }
        return largest
    }

    private fun explore(
        graph: HashMap<String, Set<String>>,
        node: String,
        visited: java.util.HashSet<String>
    ): Int {
        val queue: Queue<String> = LinkedList()
        queue.add(node)
        var componentSize = 0
        while (queue.isNotEmpty()) {
            val current = queue.poll()
            if (!visited.contains(current)) {
                visited.add(current)
                componentSize++
                graph[current]?.let {
                    for (neighbor in graph[current]!!) {
                        queue.add(neighbor)
                    }
                }
            }
        }
        return componentSize
    }
}