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

    private var _deletedUiState: CalculatorUiState? = null
    val deletedUiState: CalculatorUiState?
        get() = _deletedUiState


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
            is CalculatorAction.Undo -> performUndo()
            is CalculatorAction.Select -> performSelection(action.shoppingId)
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

        val selectedShoppingItem = uiState.shoppingList.find { it.selected }
        if (selectedShoppingItem == null) {
            val newShopping = Shopping(
                id = UUID.randomUUID().toString(),
                price = uiState.price.toIntOrNull() ?: 0,
                quantity = if (uiState.operation == null) 1 else uiState.quantity.toIntOrNull()
                    ?: 1,
                taxRate = uiState.shoppingList.maxByOrNull { it.createdAt }?.taxRate
                    ?: TaxRate.Default
            )

            uiState = uiState.copy(
                price = "",
                quantity = "",
                operation = null,
                shoppingList = (uiState.shoppingList + newShopping).sortedByDescending { it.createdAt },
                totalPrice = (uiState.shoppingList + newShopping).calculateTotalPrice()
            )
        } else {
            val updatedShoppingList = uiState.shoppingList
                .map {
                    it.copy(
                        selected = false
                    )
                }
                .map {
                    if (it.id == selectedShoppingItem.id) {
                        it.copy(
                            price = uiState.price.toIntOrNull() ?: 0,
                            quantity = if (uiState.operation == null) 1 else uiState.quantity.toIntOrNull() ?: 1
                        )
                    } else {
                        it
                    }
                }

            uiState = uiState.copy(
                price = "",
                quantity = "",
                operation = null,
                shoppingList = updatedShoppingList.sortedByDescending { it.createdAt },
                totalPrice = updatedShoppingList.calculateTotalPrice()
            )
        }
    }

    private fun performClear() {
        _deletedUiState = null

        if (uiState == CalculatorUiState()) {
            return
        }

        _deletedUiState = uiState
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

    private fun performUndo() {
        _deletedUiState?.let {
            uiState = it
            _deletedUiState = null
        }
    }

    private fun performSelection(selectedItemId: String) {
        val currentSelectedItemId = uiState.shoppingList.find { it.selected }?.id

        val updatedShoppingList = if (currentSelectedItemId == selectedItemId) {
            uiState.shoppingList.map {
                it.copy(selected = false)
            }
        } else {
            uiState.shoppingList
                .map {
                    it.copy(selected = false)
                }
                .map {
                    if (it.id == selectedItemId) {
                        it.copy(selected = true)
                    } else {
                        it
                    }
                }
        }

        val selectedShoppingItem = updatedShoppingList.find { it.selected }

        uiState = uiState.copy(
            price = selectedShoppingItem?.price?.toString().orEmpty(),
            quantity = selectedShoppingItem?.quantity?.toString().orEmpty(),
            operation = selectedShoppingItem?.let { CalculatorOperation.Multiply },
            shoppingList = updatedShoppingList,
            totalPrice = updatedShoppingList.calculateTotalPrice()
        )
    }
}