package net.zuuno.regicalc.presentation.calculator.components

import net.zuuno.regicalc.presentation.calculator.util.CalculatorOperation

sealed interface CalculatorAction {
    data class Number(val value: Int) : CalculatorAction
    object DoubleZero : CalculatorAction
    data class Operation(val operation: CalculatorOperation) : CalculatorAction
}