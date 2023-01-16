package tree.segmentTree

import kotlin.math.*

class SubsetSum(list: Array<Int>) {

    private var segmentTree: IntArray

    override fun toString(): String {
        return segmentTree.contentToString()
    }
    init {
        val height = ceil(log(list.size.toDouble(), 2.0))
        val maxSize = (height + 1).pow(2) - 1

        segmentTree = IntArray(maxSize.toInt())

        constructTree(list, 0, 0, list.size - 1)

    }

    private fun constructTree(list: Array<Int>, current: Int, start: Int, end: Int): Int {
        if (start == end) {
            segmentTree[current] = list[start]
            return segmentTree[current]
        }
        val middle = (start + end) / 2
        val left = constructTree(list, current * 2 + 1, start, middle)
        val right = constructTree(list, current * 2 + 2, middle + 1, end)
        segmentTree[current] = left + right
        return segmentTree[current]
    }

    fun getSum(start: Int, end: Int): Int {
        val queryS = max(start, 0)
        val queryE = min(end, segmentTree.size - 1)

        return _getSum(queryS, queryE, 0, segmentTree.size - 1)
    }

    private fun _getSum(queryS: Int, queryE: Int, segmentS: Int, segmentE: Int): Int {
        return if (segmentS == segmentE) {
            if (segmentS > queryS && segmentE <= queryE) {
                segmentTree[segmentS]
            } else {
                0
            }
        } else {
            val segmentM = (segmentS + segmentE) / 2
            val left = _getSum(queryS, queryE, segmentS, segmentM)
            val right = _getSum(queryS, queryE, segmentM + 1, segmentE)
            left + right
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val sum = SubsetSum(arrayOf(1, 3, 5, 7, 9, 11))
            println(sum.toString())
//            println(sum.getSum(1, 3))
        }
    }
}