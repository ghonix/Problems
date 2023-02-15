package string

/*
https://leetcode.com/problems/minimum-window-substring/
 */
class MinimumWindowSubstring {

    val tMap = HashMap<Char, Int>()
    private fun isAcceptable(map: HashMap<Char, Int>): Boolean {
        for (key in map.keys) {
            if (map[key]!! > 0) {
                return false
            }
        }
        return true
    }

    private fun shouldShrinkWindow(map: java.util.HashMap<Char, Int>): Boolean {
        if (isAcceptable(map)) {
            return true
        }
        for (key in map.keys) {
            if (map[key]!! > 0) {
                return false
            }
        }
        return true
    }

    fun minWindow(src: String, t: String): String {
        for (char in t) { // hash the count of chars in t
            if (tMap.containsKey(char)) {
                tMap[char] = tMap[char]!!.inc()
            } else {
                tMap[char] = 1
            }
        }

        var l = 0
        var r = l
        var minLength = Int.MAX_VALUE
        var rangeStart = 0
        var rangeEnd = 0
        while (r < src.length) {

            val end = src[r]
            if (tMap.containsKey(end)) {
                tMap[end] = tMap[end]!!.dec()
                r++
            } else {
                r++
            }

            while (l <= r && shouldShrinkWindow(map = tMap)) {
                if ((r - l) < minLength) {
                    minLength = r - l
                    rangeStart = l
                    rangeEnd = r
                }

                // try to shrink the window
                val start = src[l]
                if (tMap.containsKey(start)) {
                    if (tMap[start]!!.inc() <= 0) {
                        tMap[start] = tMap[start]!!.inc()
                        l++
                        continue
                    } else {
                        break
                    }
                } else {
                    l++
                }
            }
        }
        return src.substring(startIndex = rangeStart, endIndex = rangeEnd)
    }
}


fun main() {
    println(
        MinimumWindowSubstring().minWindow("ADOBECODEBANC", "ABC")
    )
}
/*
1- Hour debug (java, js, python)
* Code with issues
* Find the bugs and fix them

2- 2 hours take home
* Algorithm coding problem
* Data infra (schema design)
* System design proposal
 */