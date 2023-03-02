package prefixSum

import kotlin.math.min
/*
https://leetcode.com/problems/minimum-size-subarray-sum/
 */
class MinSubArrayLen {
    fun minSubArrayLen(target: Int, nums: IntArray): Int {
        if (nums.isEmpty()) {
            return 0
        }
        var minSize = Int.MAX_VALUE
        val prefixSums = IntArray(nums.size)
        prefixSums[0] = nums[0]
        for (i in 1 until nums.size) {
            prefixSums[i] = prefixSums[i - 1] + nums[i]
        }

        for (i in prefixSums.indices) {
            val remainder = prefixSums[i] - target
            if (remainder < 0) {
                continue
            }
            val lowerBound = search(prefixSums, 0, i - 1, prefixSums[i], target)
            if (lowerBound != -1) {
                minSize = min(minSize, i - lowerBound + 1)
            }
        }

        return if (minSize == Int.MAX_VALUE) {
            0
        } else {
            minSize
        }
    }

    private fun search(array: IntArray, low: Int, hi: Int, prefixSum: Int, target: Int): Int {
        var start = low
        var end = hi
        var mid = (start + end) / 2
        while (start <= end) {
            mid = (start + end) / 2
            if (prefixSum - array[mid] == target) {
                return mid + 1
            } else if (prefixSum - array[mid] > target) {
                start = mid + 1
            } else {
                end = mid - 1
            }
        }
        return mid
    }
}

fun main() {
    val m = MinSubArrayLen()
    println(
        m.minSubArrayLen(20, intArrayOf(1, 2, 0, 6, 14, 15))
    ) // 2

//    println(
//        m.minSubArrayLen(20, intArrayOf(2, 16, 14, 15))
//    ) // 2
//
//    println(
//        m.minSubArrayLen(7, intArrayOf(2, 3, 1, 2, 4, 3))
//    ) // 2
//
//    println(
//        m.minSubArrayLen(4, intArrayOf(1, 4, 4))
//    ) // 1
//
//    println(
//        m.minSubArrayLen(15, intArrayOf(1, 2, 3, 4, 5))
//    ) // 5
//
//    println(
//        m.minSubArrayLen(11, intArrayOf(1, 1, 1, 1, 1, 1, 1, 1))
//    ) // 0
}