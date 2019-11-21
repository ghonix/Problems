

class LongestPalindromicSubString {

    fun longestPalindrome1(s: String): String {
        val array = Array(s.length) {BooleanArray(s.length)}

        // Palindrome of size 1
        var maxLength = 1
        for (i in 0..(s.length-1)) {
            array[i][i] = true
        }

        // Palindrome of size 2
        var start = 0
        for (i in 0..(s.length - 2)) {
            if (s[i] == s[i+1]) {
                array[i][i+1] = true
                start = i
                maxLength = 2
            }
        }

        for (k in 3..(s.length)) {
            for (i in 0..(s.length-k) ) {
                var j = i + k - 1
                if (array[i+1][j-1] && s[i] == s[j]) {

                    array[i][j] = true
                    if (k > maxLength) {
                        maxLength = k
                        start = i
                    }
                }
            }
        }
        return s.substring(start, start + maxLength)
    }

    fun longestPalindrome2(s: String): String {
        var maxLength = 1
        var start = 0

        for (i in 1..(s.length-1)) {
            // Even palindromes
            var low = i - 1
            var high = i
            while(low >= 0 && high < s.length && s[low] == s[high]) {
                if (high - low + 1 > maxLength) {
                    start = low
                    maxLength = high - low + 1
                }
                low --
                high ++
            }

            low = i-1
            high = i+1
            while (low >= 0 && high < s.length && s[low] == s[high]) {
                if (high - low + 1 > maxLength) {
                    start = low
                    maxLength = high - low + 1
                }
                low --
                high ++
            }
        }

        return s.substring(start, start + maxLength)
    }
}

fun main(args: Array<String>) {
    println(LongestPalindromicSubString().longestPalindrome1("forgeeksskeegfor"))
    println(LongestPalindromicSubString().longestPalindrome2("forgeeksskeegfor"))
}