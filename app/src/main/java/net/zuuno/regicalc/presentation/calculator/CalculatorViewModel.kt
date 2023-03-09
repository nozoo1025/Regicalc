package net.zuuno.regicalc.presentation.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import net.zuuno.regicalc.presentation.calculator.util.*
import java.util.*

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
            is CalculatorAction.Add -> performAddition()
            is CalculatorAction.Clear -> performClear()
            is CalculatorAction.Delete -> performDeletion()
            is CalculatorAction.SetTaxRate -> setTaxRate(action.taxRate, action.shoppingId)
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

    private fun performAddition() {
        if (uiState.price.isBlank()) {
            return
        }

        val newShopping = Shopping(
            id = UUID.randomUUID().toString(),
            price = uiState.price.toIntOrNull() ?: 0,
            quantity = if (uiState.operation == null) 1 else uiState.quantity.toIntOrNull() ?: 1,
            taxRate = uiState.shoppingList.maxByOrNull { it.createdAt }?.taxRate ?: TaxRate.Default
        )

        uiState = uiState.copy(
            price = "",
            quantity = "",
            operation = null,
            shoppingList = (uiState.shoppingList + newShopping).sortedByDescending { it.createdAt },
            totalPrice = (uiState.shoppingList + newShopping).calculateTotalPrice()
        )
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

    private fun setTaxRate(taxRate: TaxRate, shoppingId: String) {
        val shoppingList = uiState.shoppingList.map {
            if (it.id == shoppingId) {
                if (it.taxRate == taxRate) {
                    it.copy(taxRate = TaxRate.None)
                } else {
                    it.copy(taxRate = taxRate)
                }
            } else {
                it
            }
        }

        uiState = uiState.copy(
            shoppingList = shoppingList,
            totalPrice = shoppingList.calculateTotalPrice()
        )
    }
}