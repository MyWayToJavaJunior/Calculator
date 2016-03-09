package ru.vkoba.exercise

import java.util.*

/**
 * Hello world!

 */
class Calculator {

}


class ReversePolishNotationConverter() {
    val operations = mutableListOf(
            Operation('(', false, 0),
            Operation(')', false, 0),
            Operation('+', false, 1),
            Operation('-', false, 1),
            Operation('*', false, 2),
            Operation('/', false, 3)
    )

    fun toReversePolishNotation(expr: String): String {
        val opsStack = Stack<Char>();
        var result = ""
        for (symbol in expr) {
            if (!isOperation(symbol) && !isBracket(symbol)) {
                result += symbol
                continue
            }
            if (symbol == '(') {
                opsStack.push(symbol)
                continue
            }
            if (symbol == ')') {
                while (opsStack.peek() != '(') {
                    result += " " + opsStack.pop() + " "
                }
                //Выталкиваем открывающуюся скобку
                opsStack.pop()
                continue
            }
            result += " "
            while (opsStack.isNotEmpty() && priority(symbol) <= priority(opsStack.peek())) {
                result += " " + opsStack.pop() + " "
            }
            opsStack.push(symbol)
        }
        while (opsStack.isNotEmpty()) {
            result += " " + opsStack.pop() + " "
        }
        return replaceUnneccessarySpace(result)
    }

    private fun replaceUnneccessarySpace(result: String) = result.replace("   ", " ").replace("  ", " ").trim()

    private fun priority(op: Char): Byte = operations.findLast { it -> it.symbol == op }?.priopity ?: throw RuntimeException("Unknown operation '$op'")


    fun addOperation(op: Operation) {
        operations.add(op)
    }

    private fun isBracket(symbol: Char): Boolean = "()".contains(symbol)
    private fun isOperation(symbol: Char): Boolean = operations.filter { it -> it.symbol == symbol }.isNotEmpty()
}

data class Operation(val symbol: Char, val isUnary: Boolean, val priopity: Byte)
