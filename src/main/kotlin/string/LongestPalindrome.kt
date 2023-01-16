package string

/**
 * https://leetcode.com/problems/longest-palindromic-substring
 */
import kotlin.math.max

class Solution {
    fun longestPalindrome(s: String?): String {
        if (s.isNullOrEmpty()) return ""

        var start = 0
        var end = 0
        for (i in s.indices) {
            val maxOdd = expandAroundCenter(s, i, i)
            val maxEven = expandAroundCenter(s, i, i + 1)
            val maxI = max(maxOdd, maxEven)
            if (maxI > end - start) {
                start = i - (maxI - 1) / 2
                end = i + maxI / 2
            }
        }
        return s.substring(start, end)
    }

    private fun expandAroundCenter(s: String, l: Int, r: Int): Int {
        var left = l
        var right = r
        while (left > 0 && right < s.length && s[left] == s[right]) {
            left--
            right++
        }
        return right - left - 1 // right and left are one past the real end
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

        }
    }

}