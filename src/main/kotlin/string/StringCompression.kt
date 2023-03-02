package string

/**
 * https://leetcode.com/problems/string-compression/
 */
class StringCompression {

    fun compress(chars: CharArray): Int {
        var index = 0
        var compressedIndex = 0
        while (index < chars.size) {
            var current = chars[index]

            var endOfRepeated = index + 1
            chars[compressedIndex++] = current
            while (endOfRepeated < chars.size && current == chars[endOfRepeated]) {
                endOfRepeated++
            }
            val nRepetition = endOfRepeated - index
            if (nRepetition > 1) {
                val repetitionString = nRepetition.toString()
                for (digit in repetitionString) {
                    chars[compressedIndex] = digit
                    compressedIndex++
                }
            }
            index += nRepetition
        }

        return compressedIndex
    }
}

fun main() {
    println(
        StringCompression().compress(
            "aabbcc".toCharArray()
        )
    )

    println(
        StringCompression().compress(
            "abbbbbbbbbbbb".toCharArray()
        )
    )
}