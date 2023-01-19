package graph

import java.util.*

class Dijkstra {
    private fun calculateShortestPath(): Int {
        val graph: MutableMap<String, Set<NodeData>> =
            HashMap()
        graph["1"] = mutableSetOf(
            NodeData("2", 2),
            NodeData("4", 10),
            NodeData("8", 1)
        )
        graph["2"] = mutableSetOf(NodeData("3", 3))
        graph["3"] = mutableSetOf(NodeData("4", 4))
        graph["4"] = mutableSetOf(NodeData("6", 6))
        graph["5"] = mutableSetOf(NodeData("6", 6))
        graph["6"] = mutableSetOf(
            NodeData("4", 4),
            NodeData("5", 5),
            NodeData("7", 7),
            NodeData("8", 8)
        )
        graph["7"] = mutableSetOf(NodeData("6", 6))
        graph["8"] = mutableSetOf(
            NodeData("6", 6),
            NodeData("4", 1)
        )
        return calculateShortestPath("1", "4", graph)
    }

    private fun calculateShortestPath(src: String, dst: String, graph: Map<String, Set<NodeData>>): Int {
        val queue = PriorityQueue(
            Comparator.comparingInt { obj: PathData -> obj.cost })
        val costMap: MutableMap<String, PathData> = HashMap()
        val visitedSet: MutableSet<String> = HashSet()
        val srcData: PathData = PathData(src, 0, src)
        queue.add(srcData)
        costMap[src] = srcData
        while (!queue.isEmpty()) {
            val current = queue.poll()
            if (dst == current!!.node) {
                return current.cost
            } else if (!visitedSet.contains(current.node)) {
                visitedSet.add(current.node)
                for (neighbor in graph[current.node]!!) {
                    if (costMap.containsKey(neighbor.node)) {
                        val neighborData = costMap[neighbor.node]
                        if (neighborData!!.cost > neighbor.weight + current.cost) {
                            queue.remove(neighborData)
                            neighborData.cost = neighbor.weight + current.cost
                            neighborData.from = current.from
                            queue.add(neighborData)
                        }
                    } else {
                        val neighborData: PathData =
                            PathData(neighbor.node, neighbor.weight + current.cost, current.from)
                        costMap[neighbor.node] = neighborData
                        queue.add(neighborData)
                    }
                }
            }
        }
        return Int.MAX_VALUE
    }

    data class PathData(val node: String, var cost: Int, var from: String)
    data class NodeData(val node: String, val weight: Int)
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Dijkstra().calculateShortestPath()

        }
    }
}