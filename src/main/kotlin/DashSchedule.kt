import kotlin.math.max

class DashSchedule {

    data class DashOrder(
        val startTime: Int,
        val endTime: Int,
        val profit: Int
    )

    fun jobScheduling(startTime: IntArray, endTime: IntArray, profit: IntArray): Int {
        return jobSchedulingIterative(startTime, endTime, profit)
    }

    private fun jobSchedulingRecursive(startTime: IntArray, endTime: IntArray, profit: IntArray): Int {
        val orders = getJobs(startTime, endTime, profit)
        orders.sortBy { it.startTime }
        val index = 0
        var memory = IntArray(orders.size) { -1 }
        return getMaxProfit(index, orders, memory)
    }

    private fun getMaxProfit(index: Int, orders: Array<DashOrder>, memory: IntArray): Int {
        return if (index >= orders.size) {
            0
        } else if (memory[index] > 0) {
            memory[index]
        } else if (index == orders.size - 1) {
            memory[index] = orders[index].profit
            orders[index].profit
        } else {
            // Skip to the next possible Job where current end time is <= next start time.
            var nextIndex = findNextOrder(orders[index].endTime, orders)
            max(getMaxProfit(index + 1, orders, memory), orders[index].profit + getMaxProfit(nextIndex, orders, memory))
        }

    }

    fun findNextOrder(lastEndTime: Int, orders: Array<DashOrder>): Int {
        var start = 0
        var end = orders.size - 1
        var nextIndex = orders.size
        while (start <= end) {
            var mid = (start + end) / 2
            orders[mid]
            if (orders[mid].startTime >= lastEndTime) {
                nextIndex = mid
                end = mid - 1
            } else {
                start = mid + 1
            }
        }
        return nextIndex
    }

    private fun getJobs(startTime: IntArray, endTime: IntArray, profit: IntArray) =
        Array(startTime.size) { i ->
            DashOrder(startTime[i], endTime[i], profit[i])
        }


    private fun jobSchedulingIterative(startTime: IntArray, endTime: IntArray, profit: IntArray): Int {
        val orders = getJobs(startTime, endTime, profit)
        orders.sortBy { it.startTime }
        var index = orders.size - 1
        val memory = IntArray(orders.size) { -1 }
        var maxProfit = orders[index].profit
        memory[index] = orders[index].profit
        index --

        while (index >= 0) {
            val nextIndex = findNextOrder(orders[index].endTime, orders)
            val nextProfit = if (nextIndex > orders.size - 1) {
                0
            } else {
                memory[nextIndex]
            }
            maxProfit = max(maxProfit, orders[index].profit + nextProfit)
            memory[index] = maxProfit
            index --
        }
        return maxProfit
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(
                DashSchedule().jobScheduling(
                    startTime = intArrayOf(1, 1, 1),
                    endTime = intArrayOf(2, 3, 4), profit = intArrayOf(5, 6, 4)
                )
            )
            println(
                DashSchedule().jobScheduling(
                    startTime = intArrayOf(1, 2, 3, 4, 6),
                    endTime = intArrayOf(3, 5, 10, 6, 9),
                    profit = intArrayOf(20, 20, 100, 70, 60)
                )
            )
        }
    }
}