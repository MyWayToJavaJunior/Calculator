package ru.vkoba.exercise

import org.junit.Test
import kotlin.test.assertEquals

/**
 * Created by Ricoshet on 11.03.2016.
 */


class CalculatorTest {
    private val calc = Calculator()

    @Test
    fun sum_2and2() {
        assertEquals(4.toDouble(), calc.calculate("2+2"))
    }

    @Test
    fun sum_2and2and3() {
        assertEquals(7.toDouble(), calc.calculate("2+2+3"))
    }

    @Test
    fun sum_10and100() {
        assertEquals(110.toDouble(), calc.calculate("10+100"))
    }

    @Test
    fun bracketsAndDivide() {
        assertEquals(360.toDouble(), calc.calculate("(80+2*500)/(1+3*2-4)"))
    }

}