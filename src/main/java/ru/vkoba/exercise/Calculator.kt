package ru.vkoba.exercise

import javafx.util.converter.DoubleStringConverter
import java.util.*

/**
 * Hello world!

 */
class Calculator {
    fun calculate(expr: String): Double {
        val argsStack = Stack<Double>()
        val splittedRpnExpr = ReversePolishNotationConverter().toReversePolishNotation(expr).split(" ")
        for (lexem in splittedRpnExpr) {
            if (lexem.isNumeric()) {
                argsStack.push(lexem.toDouble())
            } else {
                val arg1 = argsStack.pop()
                val arg2 = argsStack.pop()
                val temp = when (lexem) {
                    "+" -> arg2 + arg1
                    "-" -> arg2 - arg1
                    "*" -> arg2 * arg1
                    "/" -> arg2 / arg1
                    else -> throw RuntimeException("Invalid operation!")
                }
                argsStack.push(temp)
            }
        }
        return argsStack.pop()
    }
}

fun String.isNumeric(): Boolean {
    try {
        this.toDouble()
    } catch(nfe: NumberFormatException) {
        return false;
    }
    return true;
}


class ReversePolishNotationConverter() {
    val specSymbols = mutableListOf(
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
            if (!isOperation(symbol)) {
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

    private fun replaceUnneccessarySpace(result: String) = result
            .replace("  ", " ").trim()

    private fun priority(op: Char): Byte = specSymbols.findLast { it -> it.symbol == op }?.priopity ?: throw RuntimeException("Unknown operation '$op'")

    private fun isOperation(symbol: Char): Boolean = specSymbols.filter { it -> it.symbol == symbol }.isNotEmpty()
}

data class Operation(val symbol: Char, val isUnary: Boolean, val priopity: Byte)
