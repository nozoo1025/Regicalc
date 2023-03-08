package net.zuuno.regicalc.presentation.calculator

import net.zuuno.regicalc.presentation.calculator.CalculatorViewModel.Companion.MAX_NUMBER_LENGTH
import net.zuuno.regicalc.presentation.calculator.util.CalculatorOperation

data class CalculatorUiState(
    val price: String = "",
    val quantity: String = "",
    val operation: CalculatorOperation? = null
)

fun CalculatorUiState.isPriceInput(): Boolean {
    return operation == null
}

fun CalculatorUiState.isQuantityInput(): Boolean {
    return operation != null
}

fun CalculatorUiState.isInputValid(): Boolean {
    return when {
        isPriceInput() -> price.length < MAX_NUMBER_LENGTH
        isQuantityInput() -> quantity.length < MAX_NUMBER_LENGTH
        else -> false
    }
}