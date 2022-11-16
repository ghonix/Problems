class SearchInRotatedSortedArray {
    fun search(nums: IntArray, target: Int): Int {
        val rotationIndex = findRotationIndex(nums)
        if (rotationIndex == -1) {
            return binarySearch(nums, target, 0, nums.size - 1)
        } else if (rotationIndex > -1 && rotationIndex < nums.size) {
            var result = binarySearch(nums, target, 0, rotationIndex)
            if (result > -1) return result
            result = binarySearch(nums, target, rotationIndex + 1, nums.size - 1)
            return result
        }
        return -1
    }

    fun binarySearch(nums: IntArray, target: Int, start: Int, end: Int): Int {
        var start = start
        var end = end
        while (start <= end) {
            val middle = (end - start) / 2 + start
            if (target < nums[middle]) {
                end = middle - 1
            } else if (target > nums[middle]) {
                start = middle + 1
            } else {
                return middle
            }
        }
        return -1
    }

    fun findRotationIndex(nums: IntArray): Int {
        var start = 0
        var end = nums.size - 1
        while (start <= end) {
            val middle = (end - start) / 2 + start
            val middleValue = nums[middle]
            val startValue = nums[start]
            val endValue = nums[end]
            if (start == middle) {
                break
            } else if (middleValue < startValue) {
                // the rotation point is to the left
                end = middle
            } else if (middleValue > endValue) {
                // the rotation point is to the right
                start = middle
            } else {
                return -1
            }
        }
        return start
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val nums = intArrayOf(1, 3, 5)
            val target = 6
            println(SearchInRotatedSortedArray().search(nums, target))
        }
    }
}