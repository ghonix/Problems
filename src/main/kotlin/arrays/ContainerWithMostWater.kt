package arrays

import kotlin.math.min

/**
 * https://leetcode.com/problems/container-with-most-water/
 */
class ContainerWithMostWater {
    fun maxArea(height: IntArray): Int {
        if (height.size < 2) {
            return 0
        }

        var start = 0
        var end = height.size - 1
        var area = 0
        while (end > start) { // O(n)
            val currentHeight = min(height[end], height[start])
            val currentArea = (end - start) * currentHeight
            if (area < currentArea) {
                area = currentArea
            }
            if (height[end] < height[start]) {
                end --
            } else {
                start ++
            }
        }
        return area
    }
}


fun main() {
    println(ContainerWithMostWater().maxArea(intArrayOf(1,8,6,2,5,4,8,3,7)))
}