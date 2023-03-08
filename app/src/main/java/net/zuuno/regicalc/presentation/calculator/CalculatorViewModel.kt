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
            is CalculatorAction.Decimal -> enterDecimal()
            is CalculatorAction.Clear -> performClear()
            is CalculatorAction.Delete -> performDeletion()
        }
    }

    private fun enterNumber(number: Int) {
        if (!uiState.isInputValid()) {
            return
        }

        uiState = when {
            uiState.isPriceInput() -> {
                uiState.copy(price = "${uiState.price}$number")
            }
            uiState.isQuantityInput() -> {
                uiState.copy(quantity = "${uiState.quantity}$number")
            }
            else -> uiState
        }
    }

    private fun enterDoubleZero() {
        if (!uiState.isInputValid()) {
            return
        }

        repeat(2) {
            enterNumber(0)
        }
    }

    private fun enterOperation(operation: CalculatorOperation) {
        if (uiState.price.isBlank()) {
            return
        }

        uiState = uiState.copy(operation = operation)
    }

    private fun enterDecimal() {
        if (!uiState.isInputValid()) {
            return
        }

        uiState = when {
            uiState.isPriceInput() && !uiState.price.contains(".") && uiState.price.isNotBlank() -> {
                uiState.copy(
                    price = "${uiState.price}."
                )
            }
            uiState.isQuantityInput() && !uiState.quantity.contains(".") && uiState.quantity.isNotBlank() -> {
                uiState.copy(
                    quantity = "${uiState.quantity}."
                )
            }
            else -> uiState
        }

//        uiState = when {
//            uiState.operation == null && !uiState.number1.contains(".") && uiState.number1.isNotBlank() -> uiState.copy(
//                number1 = "${uiState.number1}."
//            )
//            !uiState.number2.contains(".") && uiState.number2.isNotBlank() -> uiState.copy(number2 = "${uiState.number2}.")
//            else -> uiState
//        }
    }

    private fun performClear() {
        uiState = CalculatorUiState()
    }

    private fun performDeletion() {
        uiState = when {
            uiState.quantity.isNotBlank() -> uiState.copy(quantity = uiState.quantity.dropLast(1))
            uiState.operation != null -> uiState.copy(operation = null)
            uiState.price.isNotBlank() -> uiState.copy(price = uiState.price.dropLast(1))
            else -> uiState
        }
    }
}