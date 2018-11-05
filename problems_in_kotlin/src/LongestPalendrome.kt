import com.sun.xml.internal.fastinfoset.util.StringArray

/*
Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: "cbbd"
Output: "bb"
*/

class LongestPalendrome {
    fun longestPalindrome(s: String): String {
        var i = 0
        var j = s.length - 1

        return longestPalindrome(s, i, j);
    }

    private fun longestPalindrome(s: String, i: Int, j: Int): String {
        when {
            i == j -> return s[i].toString()
            s[i] == s[j] -> return if(i + 1 == j) {
                s[i] + s[j].toString()
            } else {
                s[i] + longestPalindrome(s, i + 1, j - 1) + s[j]
            }
            else -> {
                val firstPalendrome = longestPalindrome(s, i + 1, j)
                val secondPalendrome = longestPalindrome(s, i, j - 1)
                return if (firstPalendrome.length > secondPalendrome.length) {
                    firstPalendrome
                } else {
                    secondPalendrome
                }
            }
        }
    }
}

fun main(args: Array<String>) {
    var p = LongestPalendrome().longestPalindrome("123456789_0_0_abba987654321")
    println(p)
}

//
//
//class LongestPalendrome {
//    fun longestPalindrome(s: String): String {
//        var i = 0
//        var j = s.length - 1
//
//        val longest = Array(s.length) { arrayOfNulls<String>(s.length) }
//
//        return longestPalindrome(s, i, j, longest);
//    }
//
//    private fun longestPalindrome(s: String, i: Int, j: Int, longest: Array<Array<String?>>): String {
//        return if (longest[i][j] != null) {
//            longest[i][j].toString()
//        } else if (i == j) {
//            longest[i][j] = s[i].toString()
//            s[i].toString()
//        } else if (s[i] == s[j]) {
//            var firstPalendrome = longestPalindrome(s, i+1, j, longest)
//            var secondPalendrome = longestPalindrome(s, i, j -1, longest)
//            return if (firstPalendrome.length > secondPalendrome.length) {
//                longest[i][j] = s[i] + firstPalendrome
//                longest[i][j].toString()
//            } else {
//                longest[i][j] = secondPalendrome + s[j]
//                longest[i][j].toString()
//            }
//        } else {
//            var firstPalendrome = longestPalindrome(s, i+1, j, longest)
//            var secondPalendrome = longestPalindrome(s, i, j -1, longest)
//            return if(firstPalendrome.length > secondPalendrome.length) {
//                longest[i][j] = firstPalendrome
//                longest[i][j].toString()
//            } else {
//                longest[i][j] = secondPalendrome
//                longest[i][j].toString()
//            }
//        }
//    }
//}
//
//fun main(args: Array<String>) {
//    var p = LongestPalendrome().longestPalindrome("abcbbbbbbbbbbbbbbabcaaaaaaaaaaaaaaaaaaaaaaaaaaaaabcacbabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcbbbbbbbbbbbbbbabcbbbbbbbbbbbbbbabcbbbbbbbbbbbbbb")
//    println(p)
//}