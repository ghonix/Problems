package graph

import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class Graph {
    companion object {
        @JvmStatic
        fun createAdjacencyList(edges: Array<Pair<String, String>>): Map<String, Set<String>> {
            val graph = HashMap<String, MutableSet<String>>()
            for (edge in edges) {
                if (!graph.containsKey(edge.first)) {
                    graph[edge.first] = HashSet()
                }
                if (!graph.containsKey(edge.second)) {
                    graph[edge.second] = HashSet()
                }
                graph[edge.first]?.add(edge.second)
                graph[edge.second]?.add(edge.first)
            }
            return graph
        }

        @JvmStatic
        fun main(args: Array<String>) {
            val graph = Graph()
//            graph.breadthFirst()

//            graph.findPath()

//            println(graph.findPathUndirected())

            println(graph.connectedComponents())
        }
    }

    private fun connectedComponents(): Int {
//        val edges = arrayOf(Pair("1", "2"), Pair("4", "6"), Pair("5", "6"), Pair("6", "7"), Pair("6", "8"), Pair("3"))
        val graph = HashMap<String, Set<String>>()
        graph["1"] = setOf("2")
        graph["2"] = setOf("1")
        graph["3"] = emptySet()
        graph["4"] = setOf("6")
        graph["5"] = setOf("6")
        graph["6"] = setOf("4", "5", "7", "8")
        graph["7"] = setOf("6")
        graph["8"] = setOf("6")


        val visited = HashSet<String>()
        return connectedComponents(graph, visited)
    }

    private fun connectedComponents(
        graph: HashMap<String, Set<String>>,
        visited: HashSet<String>
    ): Int {
        var connectedComponents = 0
        for (node in graph.keys) {
            if (!visited.contains(node)) {
                connectedComponents++
                explore(graph, node, visited)
            }
        }
        return connectedComponents
    }

    private fun explore(
        graph: HashMap<String, Set<String>>,
        node: String,
        visited: HashSet<String>
    ) {
        if (!visited.contains(node)) {
            visited.add(node)
            graph[node]?.let {
                for (neighbor in it) {
                    explore(graph, neighbor, visited)
                }
            }
        }
    }

    fun findPathUndirected(): Boolean {
        val edges = arrayOf(Pair("i", "j"), Pair("k", "i"), Pair("m", "k"), Pair("k", "l"), Pair("o", "n"))
        val graph = createAdjacencyList(edges)
        println(graph.toString())
        return findPathUndirected(graph, "i", "j")
    }

    private fun findPathUndirected(graph: Map<String, Set<String>>, src: String, dst: String): Boolean {
        val queue: Queue<String> = LinkedList()
        queue.add(src)
        val visited = HashSet<String>()
        while (queue.isNotEmpty()) {
            val current = queue.poll()

            if (current.equals(dst)) {
                return true
            }
            if (!visited.contains(current)) {
                visited.add(current)
                graph[current]?.let {
                    for (neighbor in graph[current]!!) {
                        queue.add(neighbor)
                    }
                }
            }
        }
        return false
    }



    fun depthFirst(graph: HashMap<String, Set<String>>, source: String) {
        val stack = Stack<String>()
        stack.push(source)
        while (stack.isNotEmpty()) {
            val current = stack.pop()
            println(current)
            graph[current]?.let {
                for (neighbor in graph[current]!!) {
                    stack.push(neighbor)
                }
            }
        }
    }

    fun breadthFirst() {
        val graph = HashMap<String, Set<String>>()
        graph["a"] = setOf("b", "c")
        graph["b"] = setOf("d")
        graph["c"] = setOf("e")
        graph["d"] = setOf("f")
        graph["e"] = emptySet()
        graph["f"] = emptySet()

        breadthFirst(graph, "a")
    }

    fun breadthFirst(graph: HashMap<String, Set<String>>, source: String) {
        val queue: Queue<String> = LinkedList()
        queue.add(source)
        while (queue.isNotEmpty()) {
            val current = queue.poll()
            println(current)
            graph[current]?.let {
                for (neighbor in graph[current]!!) {
                    queue.add(neighbor)
                }
            }
        }
    }

    private fun findPath() {
        val graph = HashMap<String, Set<String>>()
        graph["f"] = setOf("g", "i")
        graph["g"] = setOf("h")
        graph["h"] = emptySet()
        graph["i"] = setOf("g", "k")
        graph["j"] = setOf("i")
        graph["k"] = emptySet()

        println(this.findPath(graph, "j", "h"))

    }

    private fun findPath(graph: java.util.HashMap<String, Set<String>>, src: String, dst: String): Boolean {
        val queue: Queue<String> = LinkedList()
        queue.add(src)
        while (queue.isNotEmpty()) {
            val current = queue.poll()

            if (current.equals(dst)) {
                return true
            }
            graph[current]?.let {
                for (neighbor in graph[current]!!) {
                    queue.add(neighbor)
                }
            }
        }

        return false
    }
}