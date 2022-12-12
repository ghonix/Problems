package expression

import java.lang.UnsupportedOperationException
import java.util.*

class EvaluateExpression {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(EvaluateExpression().evaluate("3+4*5"))
        }
    }

    private fun evaluate(expression: String): Int {
        val operations = Stack<Char>()
        var values = Stack<Int>()
        val tokens = expression.toCharArray()
        var i = 0
        while (i in tokens.indices) {
            if (tokens[i] == ' ') {
                continue
            }
            if (tokens[i] in '0'..'9') {
                val buffer = StringBuffer()
                while (i < tokens.size && tokens[i] in '0'..'9') {
                    buffer.append(tokens[i++])
                }
                i--
                values.add(Integer.parseInt(buffer.toString()))
            } else if (tokens[i] == '+' ||
                tokens[i] == '-' ||
                tokens[i] == '*' ||
                tokens[i] == '/'
            ) {

                while (operations.isNotEmpty() && hasPrecedence(tokens[i], operations.peek())) {
                    values.push(applyOperation(operations.pop(), values.pop(), values.pop()))
                }

                operations.push(tokens[i])
            }
            i++
        }
        while (operations.isNotEmpty()) {
            values.push(applyOperation(operations.pop(), values.pop(), values.pop()))
        }

        return values.pop()
    }

    private fun applyOperation(op: Char, val1: Int, val2: Int): Int {
        return when(op) {
            '+' -> val1 + val2
            '-' -> val1 - val2
            '*' -> val1 * val2
            '/' -> {
                if (val2 == 0) {
                    throw UnsupportedOperationException("Divide by Zero")
                } else {
                    val1 / val2
                }
            }
            else -> 0
        }
    }

    private fun hasPrecedence(op1: Char, op2: Char): Boolean {
        return if ((op1 == '*' || op1 == '/') &&
            (op2 == '+' || op2 == '-')
        ) {
            false
        } else {
            true
        }
    }

}