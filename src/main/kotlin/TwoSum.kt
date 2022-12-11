/**
 * Created by aghoneim on 4/13/17.
 */
object TwoSum {
    fun twoSum(nums: IntArray, target: Int): IntArray? {
        val numberSet: MutableMap<Int, Int> = HashMap()
        for (i in nums.indices) {
            val value = numberSet[target - nums[i]]
            if (value != null) {
                return intArrayOf(value, i)
            } else {
                numberSet[nums[i]] = i
            }
        }
        return null
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val result = twoSum(intArrayOf(3, 0, 7, 11, 4, 15), 15)
        if (result != null) {
            println(String.format("%d, %d", result[0], result[1]))
        } else {
            println("Result Not Found!")
        }
    }
}