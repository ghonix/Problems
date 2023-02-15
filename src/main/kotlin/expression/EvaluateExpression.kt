package expression

import java.lang.UnsupportedOperationException
import java.util.*

class EvaluateExpression {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(EvaluateExpression().calculate("(1+2+3)*3"))
        }
    }

    fun calculate(expression: String): Int {
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
            } else if (tokens[i] == ')') {
                while (operations.isNotEmpty() && operations.peek() != '(') {
                    values.push(applyOperation(operations.pop(), values.pop(), values.pop()))
                }
                if (operations.isNotEmpty() && operations.peek() == '(') {
                    operations.pop()
                }
            } else if (tokens[i] == '(') {
                operations.push(tokens[i])
            } else if (tokens[i] == '+' ||
                tokens[i] == '-' ||
                tokens[i] == '*' ||
                tokens[i] == '/'
            ) {

                while (operations.isNotEmpty() && hasPrecedence(operations.peek(), tokens[i])) {
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

    private fun applyOperation(op: Char, b: Int, a: Int): Int {
        return when (op) {
            '+' -> a + b
            '-' -> a - b
            '*' -> a * b
            '/' -> {
                if (b == 0) {
                    throw UnsupportedOperationException("Divide by Zero")
                } else {
                    a / b
                }
            }

            else -> 0
        }
    }

    private fun hasPrecedence(op1: Char, op2: Char): Boolean {
        if (op1 == '(' || op1 == ')') return false
        if ((op2 == '*' || op2 == '/') && (op1 == '+' || op1 == '-')) return false
        return true
    }

}