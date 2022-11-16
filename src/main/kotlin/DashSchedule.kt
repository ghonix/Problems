import kotlin.math.max

class DashSchedule {

    data class DashOrder(
        val startTime: Int,
        val endTime: Int,
        val profit: Int,
    )


    fun jobScheduling(startTime: IntArray, endTime: IntArray, profit: IntArray): Int {
        val orders = getJobs(startTime, endTime, profit)
        orders.sortBy { it.startTime }
        val index = 0
        return getMaxProfit(index, orders)
    }

    private fun getMaxProfit(index: Int, orders: Array<DashOrder>): Int {
        return if (index >= orders.size) {
            0
        } else if (index == orders.size - 1) {
            orders[index].profit
        } else {
            // Skip to the next possible Job where current end time is <= next start time.
            var nextIndex = index + 1
            while (nextIndex < orders.size) {
                if (orders[nextIndex].startTime >= orders[index].endTime) {
                    break
                }
                nextIndex++
            }
            max(getMaxProfit(index + 1, orders), orders[index].profit + getMaxProfit(nextIndex, orders))
        }
    }

    private fun getJobs(startTime: IntArray, endTime: IntArray, profit: IntArray) =
        Array(startTime.size) { i ->
            DashOrder(startTime[i], endTime[i], profit[i])
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
                    startTime = intArrayOf(1, 2, 3, 3),
                    endTime = intArrayOf(3, 4, 5, 6),
                    profit = intArrayOf(50, 10, 40, 70)
                )
            )
        }
    }
}