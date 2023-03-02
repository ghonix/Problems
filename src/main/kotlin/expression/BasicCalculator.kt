package expression

import java.lang.IllegalArgumentException
import java.lang.StringBuilder
import java.util.Stack

class BasicCalculator {

    fun calculate(s: String): Int {
        val ops = Stack<Char>()
        val values = Stack<Int>()

        var i = 0
        while (i < s.length) {
            if (s[i] == ' ') {
                i++
                continue
            }

            if (isNumber(s[i])) {
                val currentNumber = StringBuilder()
                while (i < s.length && isNumber(s[i])) {
                    currentNumber.append(s[i])
                    i++
                }
                i--
                values.add(Integer.parseInt(currentNumber.toString()))
            } else if (isOperation(s[i])) {
                while (ops.isNotEmpty() && ops.peek().hasPrecedence(s[i])) {
                    val val2 = values.pop()
                    val val1 = values.pop()
                    val op = ops.pop()
                    values.push(applyOp(op, val1, val2))
                }
                ops.push(s[i])
            }
            i++
        }

        while (ops.isNotEmpty()) {
            val val2 = values.pop()
            val val1 = values.pop()
            val op = ops.pop()
            values.push(applyOp(op, val1, val2))
        }
        return values.peek()
    }

    private fun applyOp(op: Char?, val1: Int, val2: Int): Int? {
        return when (op) {
            '+' -> val1 + val2
            '-' -> val1 - val2
            '*' -> val1 * val2
            '/' -> {
                if (val2 == 0) {
                    throw IllegalArgumentException("Division By zero!")
                } else {
                    val1 / val2
                }
            }

            else -> null

        }
    }

    fun isNumber(token: Char): Boolean{
        val digit = token - '0'
        return digit >= 0 && digit <= 9
    }

    fun isOperation(token: Char) = "+-*/".contains(token)
    private fun Char.hasPrecedence(c: Char): Boolean {
        return !("+-".contains(this) && "*/".contains(c))
    }
}

fun main() {

    println(
        BasicCalculator().calculate(" 1-1+1 ")
    )
}