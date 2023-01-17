package string

class KMP {
    fun search(s: String, pattern: String) {
        val lps = preCompute(pattern)

        var i = 0
        var j = 0
        while ((s.length - i) > (pattern.length - j)) {
            if (j < pattern.length && s[i] == s[j]) {
                i++
                j++
            }
            if (j == pattern.length) {
                println("Found a match %d".format(i - j))
                i = lps[j - 1]
            } else if (i < s.length && s[i] != pattern[j]) {
                if (j != 0) {
                    j = lps[j - 1]
                } else {
                    i++
                }
            }
        }
    }

    private fun preCompute(pattern: String): Array<Int> {
        val lps = Array(pattern.length) { 0 }
        var len = 0
        var i = 1

        while (i < pattern.length) {
            if (pattern[i] == pattern[len]) {
                len++
                lps[i] = len
                i++
            } else {
                if (len != 0) {
                    len = lps[len - 1]
                } else {
                    lps[i] = len
                    i++
                }
            }
        }

        return lps
    }
//                                 i          1  2  3  3  4  5  6  7  7  8
//                                len         0  1  0  0  0  1  2  3  2  3
//                             pattern[i]  a  a  a  c  c  a  a  a  a
//                                lps[i]   0  1  2  0  0  1  2  3  3  3  4

// For the pattern “AAACAAAAAC”, lps[] is [0, 1, 2, 0, 1, 2, 3, 3, 3, 4]


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val txt = "ABABDABACDABABCABAB"
            val pat = "ABABCABAB"
            KMP().search(pat, txt)
        }
    }
}