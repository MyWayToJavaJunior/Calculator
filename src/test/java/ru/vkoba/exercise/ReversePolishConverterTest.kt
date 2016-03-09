package ru.vkoba.exercise

import org.junit.Test
import kotlin.test.assertEquals


/**
 * Unit test for simple App.
 */
class ReversePolishConverterTest {
    private val rpnc = ReversePolishNotationConverter()

    @Test
    fun sum_2and2() {
        assertEquals("2 2 +", rpnc.toReversePolishNotation("2+2"))
    }

    @Test
    fun sum_2and2and3() {
        assertEquals("2 2 + 3 +", rpnc.toReversePolishNotation("2+2+3"))
    }

    @Test
    fun sum_10and100() {
        assertEquals("10 100 +", rpnc.toReversePolishNotation("10+100"))
    }
    @Test
    fun bracketsAndDivide(){
        assertEquals("8 2 5 * + 1 3 2 * + 4 - /", rpnc.toReversePolishNotation("(8+2*5)/(1+3*2-4)"))
    }
}
