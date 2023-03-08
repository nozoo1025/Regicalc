package net.zuuno.regicalc.presentation.calculator

import net.zuuno.regicalc.presentation.calculator.util.CalculatorOperation

data class CalculatorUiState(
    val number1: String = "",
    val number2: String = "",
    val operation: CalculatorOperation? = null
)
