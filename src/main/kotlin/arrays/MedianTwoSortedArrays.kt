package arrays

/**
 * https://leetcode.com/problems/median-of-two-sorted-arrays/#/description
 * Created by aghoneim on 4/18/17.
 */
class MedianTwoSortedArrays {
    fun findMedianSortedArrays(nums1: IntArray?, nums2: IntArray?): Double {
        var nums1 = nums1
        var nums2 = nums2
        if (nums1 == null) {
            nums1 = intArrayOf()
        }
        if (nums2 == null) {
            nums2 = intArrayOf()
        }
        val size = nums1.size + nums2.size
        return if (size > 0) {
            var firstMedian = 0
            var secondMedian = 0
            var i = 0
            var j = 0
            var medianIndex = 0
            val middle = (size - 1) / 2
            while (i < nums1.size || j < nums2.size) {
                var current = 0
                if (i >= nums1.size) {
                    current = nums2[j]
                    j++
                } else if (j >= nums2.size) {
                    current = nums1[i]
                    i++
                } else if (nums1[i] < nums2[j]) {
                    current = nums1[i]
                    i++
                } else {
                    current = nums2[j]
                    j++
                }
                if (medianIndex == middle) {
                    firstMedian = current
                } else if (medianIndex == middle + 1) {
                    secondMedian = current
                } else if (medianIndex > middle) {
                    break
                }
                medianIndex++
            }
            if (size % 2 == 0) (firstMedian + secondMedian) / 2.0 else firstMedian.toDouble()
        } else {
            0.0
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val m = MedianTwoSortedArrays()
            println(m.findMedianSortedArrays(intArrayOf(0, 2, 3, 3), intArrayOf(4, 5)))
        }
    }
}