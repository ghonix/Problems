package string

/*
https://leetcode.com/problems/shortest-palindrome/solutions/
 */
class ShortestPalindrome {


    fun shortestPalindrome(s: String): String {
        if (s.length < 2) return s

        val prime = 31
        val mod = 1e9 + 7
        var powersOfPrime: Long = 31

        var index = 0
        var forwardHash: Long = (s[0] - 'a' + 1).toLong()
        var reverseHash: Long = (s[0] - 'a' + 1).toLong()


        for (i in 1 until s.length) {
            forwardHash += (s[i] - 'a' + 1) * powersOfPrime
            reverseHash = reverseHash * prime + (s[i] - 'a' + 1).toLong()

            forwardHash = (forwardHash % mod).toLong()
            reverseHash = (reverseHash % mod).toLong()

            powersOfPrime *= prime
            powersOfPrime = (powersOfPrime % mod).toLong()

            if (forwardHash == reverseHash) {
                index = i
            }
        }

        return s.substring(index + 1).reversed() + s
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(ShortestPalindrome().shortestPalindrome("aacecaaa"))
        }
    }

}