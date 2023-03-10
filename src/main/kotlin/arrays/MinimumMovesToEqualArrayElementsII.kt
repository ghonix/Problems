package arrays

import kotlin.math.abs

/**
 * https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/description/
 */
class MinimumMovesToEqualArrayElementsII {

    fun minMoves2(nums: IntArray): Int {
        nums.sort()
        var i = 0
        var j = nums.size - 1
        var numMoves = 0
        while (i < j) {
            numMoves += nums[j] - nums[i]
            j--
            i++
        }
        return numMoves
    }

    fun swap(nums: IntArray, first: Int, second: Int) {
        var tmp = nums[first]
        nums[first] = nums[second]
        nums[second] = tmp
    }
}

fun main() {

    println(
        MinimumMovesToEqualArrayElementsII().minMoves2(intArrayOf(1, 10, 2, 9))
    )
}