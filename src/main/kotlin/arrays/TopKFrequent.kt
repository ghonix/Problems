package arrays

import java.util.*

class TopKFrequent {
    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        val map = HashMap<Int, Int>()
        for (num in nums) {
            map[num] = map.getOrDefault(num, 0) + 1
        }

        val queue = PriorityQueue<Map.Entry<Int, Int>> { a, b ->
            b.value - a.value
        }

        for (entry in map) {
            queue.add(entry)
        }
        val result = IntArray(k)
        for (i in 0 until k) {
            result[i] = queue.poll().key
        }

        return result
    }
}

fun main() {
    println(
        TopKFrequent().topKFrequent(
            intArrayOf(
                1, 1, 1, 2, 2, 3
            ), 2
        )
    )
}