package expression

import java.util.*

class ExpressionCombinations {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            println(ExpressionCombinations().diffWaysToCompute("2-2-1").toString())
        }
    }

    fun diffWaysToCompute(expression: String): List<Int> {
        val memory = HashMap<String, LinkedList<Int>>()
        return diffWaysToCompute(expression, memory)
    }


    fun diffWaysToCompute(expression: String, memory: HashMap<String, LinkedList<Int>>): List<Int> {
        var results: LinkedList<Int> = memory[expression] ?: LinkedList()

        if (results.isNotEmpty()) {
            return results
        }

        for (i in expression.indices) {
            if (expression[i] == '+' ||
                expression[i] == '-' ||
                expression[i] == '*'
            ) {
                val leftWays = diffWaysToCompute(expression.substring(0, i), memory)
                val rightWays = diffWaysToCompute(expression.substring(i + 1), memory)
                for (left in leftWays) {
                    for (right in rightWays) {
                        results.add(
                            when (expression[i]) {
                                '+' -> left + right
                                '-' -> left - right
                                '*' -> left * right
                                else -> 0
                            }
                        )
                    }
                }
            }
        }
        if (results.isEmpty()) {
            results.add(expression.toInt())
        }

        return results
    }
}