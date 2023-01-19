package string

/**
 * https://leetcode.com/problems/valid-number/description/
 */
class IsNumer {
    enum class TokenType {
        DIGIT,
        SIGN,
        DOT,
        EXPONENT,
        INVALID
    }

    private val dfa = hashMapOf(
        Pair(0, hashMapOf(Pair(TokenType.DIGIT, 1), Pair(TokenType.SIGN, 2), Pair(TokenType.DOT, 3))), // Start
        Pair(1, hashMapOf(Pair(TokenType.DIGIT, 1), Pair(TokenType.DOT, 4), Pair(TokenType.EXPONENT, 5))), // digit
        Pair(2, hashMapOf(Pair(TokenType.DIGIT, 1), Pair(TokenType.DOT, 3))), // optional sign
        Pair(3, hashMapOf(Pair(TokenType.DIGIT, 4))), // dot
        Pair(
            4,
            hashMapOf(Pair(TokenType.DIGIT, 4), Pair(TokenType.EXPONENT, 5))
        ), // this is the digit state after a dot
        Pair(5, hashMapOf(Pair(TokenType.DIGIT, 7), Pair(TokenType.SIGN, 6))), // exponent
        Pair(6, hashMapOf(Pair(TokenType.DIGIT, 7))), // sign after exponent e+
        Pair(7, hashMapOf(Pair(TokenType.DIGIT, 7))) // digit after exponent e7
    )
    private val validStates = setOf(1, 4, 7)

    fun isNumber(s: String): Boolean {
        var state = 0

        for (token in s) {
            val tokenType = getTokenType(token)
            if (tokenType == TokenType.INVALID) {
                return false
            } else {
                val tempState = transition(state, tokenType)
                if (tempState == null) {
                    return false
                } else {
                    state = tempState
                }
            }
        }

        return isValidEndState(state)
    }

    private fun isValidEndState(state: Int): Boolean {
        return validStates.contains(state)
    }


    private fun transition(state: Int, tokenType: TokenType): Int? {
        val currentState = dfa[state]
        return currentState?.let { it[tokenType] }
    }

    private fun getTokenType(token: Char): TokenType {
        return when (token) {
            '+' -> TokenType.SIGN
            '-' -> TokenType.SIGN
            '.' -> TokenType.DOT
            in '0'..'9' -> TokenType.DIGIT
            in "eE" -> TokenType.EXPONENT
            else -> TokenType.INVALID
        }
    }
}

fun main() {
    println(IsNumer().isNumber("a+1"))
    println(IsNumer().isNumber("1"))
    println(IsNumer().isNumber("+123"))
    println(IsNumer().isNumber(".1"))
    println(IsNumer().isNumber("+1e3"))
    println(IsNumer().isNumber("+1e3.5"))
    println(IsNumer().isNumber("+1E3.5"))
    println(IsNumer().isNumber("+1E-356"))
    println(IsNumer().isNumber("+1E+3"))
    println(IsNumer().isNumber("+1.0E+3"))
    println(IsNumer().isNumber("+1.234E+3"))
    println(IsNumer().isNumber(".E3"))
}