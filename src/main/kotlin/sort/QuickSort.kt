package sort

import java.lang.Math.random
import java.util.*
import kotlin.math.roundToInt
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

class QuickSort {

    fun quickSort(list: IntArray, lo: Int, hi: Int) {
        val stack = Stack<Int>()
        stack.push(lo)
        stack.push(hi)
        while (stack.isNotEmpty()) {
            var hi = stack.pop()
            var lo = stack.pop()
            val pivot = partition(list, lo, hi)
            if (pivot - 1 > lo) {
                stack.push(lo)
                stack.push(pivot - 1)
            }
            if (pivot + 1 < hi) {
                stack.push(pivot + 1)
                stack.push(hi)
            }
        }
    }

    private fun partition(list: IntArray, lo: Int, hi: Int): Int {
        val pivot = list[hi]
        var i = lo - 1
        for (j in lo until hi) {
            if (list[j] < pivot) {
                i++
                val tmp = list[i]
                list[i] = list[j]
                list[j] = tmp
            }
        }
        i++
        val tmp = list[i]
        list[i] = list[hi]
        list[hi] = tmp
        return i
    }


    companion object {
        @OptIn(ExperimentalTime::class)
        @JvmStatic
        fun main(args: Array<String>) {
            var values = IntArray(1000000) { i -> random().roundToInt() }
            val randomized = measureTime {
                QuickSort().quickSort(values, 0, values.size - 1)
            }
            println("Iterative took: $randomized")
        }
    }
}