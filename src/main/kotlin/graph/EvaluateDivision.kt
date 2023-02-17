/**
 * https://leetcode.com/problems/evaluate-division/
 */
package graph

import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet
import kotlin.collections.LinkedHashSet

class EvaluateDivision {
    data class Edge(val division: Double, val to: String)

    fun calcEquation(equations: List<List<String>>, values: DoubleArray, queries: List<List<String>>): DoubleArray {
        if (equations.size != values.size) {
            return doubleArrayOf(-1.0)
        }


        val graph = HashMap<String, MutableSet<Edge>>()


        // construct the division graph
        for (i in equations.indices) {
            if (equations[i].size < 2) {
                return doubleArrayOf(-1.0)
            }
            val first = equations[i][0]
            val second = equations[i][1]
            val division = values[i]
            graph.putIfAbsent(first, LinkedHashSet())
            graph.putIfAbsent(second, LinkedHashSet())

            graph[first]?.add(Edge(division, second))
            graph[second]?.add(Edge(1/division, first))
        }


        val output = Vector<Double>()
        for (query in queries) {
            output.add(calculate(graph, query[0], query[1]))
        }

        return output.toDoubleArray()
    }

    data class PathData(val node: String, val cost: Double)

    private fun calculate(graph: HashMap<String, MutableSet<Edge>>, src: String, dst: String): Double? {
        if (!graph.containsKey(src)) {
            return -1.0
        }

        val visited = HashSet<String>()
        val queue: Queue<PathData> = LinkedList()

        queue.add(PathData(src, 1.0))

        while(queue.isNotEmpty()) {
            val current = queue.poll()

            if (current.node == dst) {
                return current.cost
            }
            if (visited.contains(current.node)) {
                continue
            }

            visited.add(current.node)
            val neighbors = graph[current.node]
            if (neighbors != null) {
                for(neighbor in neighbors) {
                    queue.add(PathData(neighbor.to, neighbor.division * current.cost))
                }
            }
        }
        return -1.0
    }
}