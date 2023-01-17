package string

/**
 * https://leetcode.com/problems/string-to-integer-atoi/
 */
class MyAtoI {

    fun myAtoi(s: String): Int {
        if (s.isNullOrEmpty()) return 0
        var sign = 1
        var index = 0
        var result = 0
        var digit = 0
        val length = s.length
        while (index < length && s[index] == ' ') {
            index++
        }
        if (index < length && s[index] == '+') {
            sign = 1
            index++
        } else if (index < length && s[index] == '-') {
            sign = -1
            index++
        }
        while (index < length) {
            digit = s[index] - '0'
            if (digit in 0..9) {
                if ((result > (Int.MAX_VALUE / 10)) ||
                    (result == Int.MAX_VALUE / 10 && digit > (Int.MAX_VALUE % 10))
                ) {
                    return if (sign > 0) {
                        Int.MAX_VALUE
                    } else {
                        Int.MIN_VALUE
                    }
                } else {
                    result = result * 10 + digit
                }
            } else {
                break
            }
            index++
        }
        return sign * result
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            MyAtoI().myAtoi("42")
        }
    }
}