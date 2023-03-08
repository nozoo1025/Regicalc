package net.zuuno.regicalc.presentation.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import net.zuuno.regicalc.presentation.calculator.components.CalculatorAction
import net.zuuno.regicalc.presentation.calculator.util.CalculatorOperation

class CalculatorViewModel : ViewModel() {

    var uiState by mutableStateOf(CalculatorUiState())
        private set

    companion object {
        const val MAX_NUMBER_LENGTH = 8
    }

    fun onAction(action: CalculatorAction) {
        when (action) {
            is CalculatorAction.Number -> enterNumber(action.value)
            is CalculatorAction.DoubleZero -> enterDoubleZero()
            is CalculatorAction.Operation -> enterOperation(action.operation)
            is CalculatorAction.Clear -> performClear()
        }
    }

    private fun enterNumber(number: Int) {
        if (!uiState.isInputValid()) {
            return
        }

        uiState = when {
            uiState.isPriceInput() -> {
                val newPrice = "${uiState.price}$number".toIntOrNull() ?: 0
                uiState.copy(price = newPrice.toString())
            }
            uiState.isQuantityInput() -> {
                val newQuantity = "${uiState.quantity}$number".toIntOrNull() ?: 0
                uiState.copy(quantity = newQuantity.toString())
            }
            else -> uiState
        }
    }

    private fun enterDoubleZero() {
        if (!uiState.isInputValid()) {
            return
        }

        uiState = when {
            uiState.isPriceInput() -> {
                val newPrice = ("${uiState.price}00".toIntOrNull() ?: 0).toString()
                if (newPrice.length > MAX_NUMBER_LENGTH) {
                    uiState.copy(price = newPrice.substring(0, MAX_NUMBER_LENGTH))
                } else {
                    uiState.copy(price = newPrice)
                }
            }
            uiState.isQuantityInput() -> {
                val newQuantity = ("${uiState.quantity}00".toIntOrNull() ?: 0).toString()
                if (newQuantity.length > MAX_NUMBER_LENGTH) {
                    uiState.copy(quantity = newQuantity.substring(0, MAX_NUMBER_LENGTH))
                } else {
                    uiState.copy(quantity = newQuantity)
                }
            }
            else -> uiState
        }
    }

    private fun enterOperation(operation: CalculatorOperation) {
        if (uiState.price.isBlank()) {
            return
        }

        uiState = uiState.copy(operation = operation)
    }

    private fun performClear() {
        uiState = CalculatorUiState()
    }
}