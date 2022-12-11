import kotlin.math.max

class RodCutting {

    fun cutRodRecursively(profit: IntArray, rodLength: Int): Int {
        if (rodLength == 0) {
            return 0
        }
        var maximumProfit = Int.MIN_VALUE
        for (i in 1..rodLength) {
            maximumProfit = max(maximumProfit, profit[i] + cutRodRecursively(profit, rodLength - i))
        }
        return maximumProfit
    }

    fun cutRodIteratively(profit: IntArray, rodLength: Int): Int {
        val cache = IntArray(rodLength + 1) { 0 }
        for (i in 1..rodLength) {
            var maximum = Int.MIN_VALUE
            for (j in 1..i) {
                maximum = max(maximum, profit[j] + cache[i - j])
            }
            cache[i] = maximum
        }
        return cache[rodLength]
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            val profit = intArrayOf(0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30, 30, 30)
            println(RodCutting().cutRodRecursively(profit, 12))
            println(RodCutting().cutRodIteratively(profit, 12))
        }
    }
}