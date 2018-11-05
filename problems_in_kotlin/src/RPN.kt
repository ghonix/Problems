import java.lang.IllegalArgumentException
import java.util.*

/** Compute the value of an expression in Reverse Polish Notation. Supported operators are "+", "-", "*" and "/".
 * Reverse Polish is a postfix mathematical notation in which each operator immediately follows its operands.
 * Each operand may be a number or another expression.
 * For example, 3 + 4 in Reverse Polish is 3 4 + and 2 * (4 + 1) would be written as 4 1 + 2 * or 2 4 1 + *
 *
 * @param ops a sequence of numbers and operators, in Reverse Polish Notation
 * @return the result of the computation
 * @throws IllegalArgumentException ops don't represent a well-formed RPN expression
 * @throws ArithmeticException the computation generates an arithmetic error, such as dividing by zero
 *
 * Some sample ops and their results:
 * ["4", "1", "+", "2.5", "*"] -> ((4 + 1) * 2.5) -> 12.5
 * ["5", "80", "40", "/", "+"] -> (5 + (80 / 40)) ->  7
 */


fun rpn(ops: List<String>): Double {
    var operandStack = Stack<Double>()
    for (operand in ops) {
        if (operand.toDoubleOrNull() != null) {
            operandStack.push(operand.toDouble())
        } else if (isOperator(operand)) {
            var secondOperand = operandStack.pop();
            var firstOperand = operandStack.pop();
            operandStack.push(performOperation(firstOperand, secondOperand, operand))
        }
    }
    return operandStack.pop()
}

fun performOperation(firstOperand: Double, secondOperand: Double, operand: String) = when (operand) {
    "+" -> (firstOperand + secondOperand)
    "-" -> (firstOperand - secondOperand)
    "*" -> firstOperand * secondOperand
    "/" -> firstOperand / secondOperand
    else -> throw IllegalArgumentException("Invalid operator")
}

fun isOperator(operand: String) = when (operand) {
    "+", "-", "*", "/" -> true
    else -> false
}

fun main(args: Array<String>) {
    println(rpn(listOf("4", "1", "+", "2.5", "*")))
    println(rpn(listOf("5", "80", "40", "/", "+")))
}