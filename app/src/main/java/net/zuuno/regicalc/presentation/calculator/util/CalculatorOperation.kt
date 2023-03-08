package net.zuuno.regicalc.presentation.calculator.util

sealed class CalculatorOperation(val symbol: String) {
    object Multiply : CalculatorOperation("×")
}
