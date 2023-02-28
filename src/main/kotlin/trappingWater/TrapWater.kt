package trappingWater

import java.util.Stack
import kotlin.math.min

class TrapWater {
    fun trap(height: IntArray): Int {
        var answer = 0
        val stack = Stack<Int>()
        for (i in height.indices) {
            while (stack.isNotEmpty() && height[i] > height[stack.peek()]) {
                val top = height[stack.pop()]
                if (stack.isEmpty()) {
                    break
                }
                val width = i - stack.peek() - 1
                val height = min(height[i], height[stack.peek()]) - top

                answer += width * height

            }
            stack.push(i)
        }
        return answer
    }
}

fun main() {
    println(
        TrapWater().trap(
            intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)
        )
    )
}