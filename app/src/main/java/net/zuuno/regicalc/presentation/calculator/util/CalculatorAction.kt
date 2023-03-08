package net.zuuno.regicalc.presentation.calculator.util

sealed interface CalculatorAction {
    data class Number(val value: Int) : CalculatorAction
    object DoubleZero : CalculatorAction
    data class Operation(val operation: CalculatorOperation) : CalculatorAction
    object Decimal : CalculatorAction
    object Add : CalculatorAction
    object Clear : CalculatorAction
    object Delete : CalculatorAction
}