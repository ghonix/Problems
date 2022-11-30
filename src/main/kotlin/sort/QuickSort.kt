package sort

import java.util.*

class QuickSort {

    data class Range(val lo: Int, val hi: Int)
    val rangeStack = Stack<Range>()

    fun sort(list: IntArray) {
        if (list.size > 1) _sortIterative(list, 0, list.size - 1)
    }

    private fun _sortIterative(list: IntArray, lo: Int, hi: Int) {
        rangeStack.push(Range(lo, hi))
        while (rangeStack.isNotEmpty()) {
            val range = rangeStack.pop()
            if (range.lo < range.hi && range.lo >= 0) {
                val pivot = findPivot(list, range.lo, range.hi)
                rangeStack.push(Range(range.lo, pivot - 1))
                rangeStack.push(Range(pivot + 1, range.hi))
            }
        }
    }

    private fun _sortRecursive(list: IntArray, lo: Int, hi: Int) {
        if (lo >= hi || lo < 0)
            return
        var pivot = findPivot(list, lo, hi)
        _sortRecursive(list, lo, pivot - 1)
        _sortRecursive(list, pivot + 1, hi)
    }

    private fun findPivot(list: IntArray, lo: Int, hi: Int): Int {
        var pivot = list[hi]
        var i = lo - 1
        for (j in lo until hi) {
            if (list[j] <= pivot) {
                i++
                val tmp = list[j]
                list[j] = list[i]
                list[i] = tmp
            }
        }
        i++
        val tmp = list[hi]
        list[hi] = list[i]
        list[i] = tmp
        return i
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var values = intArrayOf(3, 6, 3, 8, 9, 10, -1, 3, 2, -10, -20, 0)
            QuickSort().sort(values)
            println(values.contentToString())

        }
    }
}