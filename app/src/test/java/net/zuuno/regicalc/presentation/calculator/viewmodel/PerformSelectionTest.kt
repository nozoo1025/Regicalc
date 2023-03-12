package net.zuuno.regicalc.presentation.calculator.viewmodel

import com.google.common.truth.Truth.assertThat
import net.zuuno.regicalc.presentation.calculator.CalculatorViewModel
import net.zuuno.regicalc.presentation.calculator.util.CalculatorAction
import net.zuuno.regicalc.presentation.calculator.util.CalculatorOperation
import org.junit.Before
import org.junit.Test

class PerformSelectionTest {

    private lateinit var viewModel: CalculatorViewModel

    @Before
    fun setUp() {
        viewModel = CalculatorViewModel()
    }

    @Test
    fun testPerformSelection_withSelectedItemUnselected_shouldSelectTheItem() {
        // Given
        viewModel.onAction(CalculatorAction.Number(1))
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Number(3))
        viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Add)

        // When
        val targetItem = viewModel.uiState.shoppingList.first()
        viewModel.onAction(CalculatorAction.Select(targetItem.id))

        // Then
        assertThat(viewModel.uiState.shoppingList.first().selected).isTrue()
        assertThat(viewModel.uiState.price).isEqualTo("123")
        assertThat(viewModel.uiState.operation).isEqualTo(CalculatorOperation.Multiply)
        assertThat(viewModel.uiState.quantity).isEqualTo("2")
    }

    @Test
    fun testPerformSelection_withSelectedItemSelected_shouldUnselectTheItem() {
        // Given
        viewModel.onAction(CalculatorAction.Number(1))
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Number(3))
        viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Add)

        val targetItem = viewModel.uiState.shoppingList.first()
        viewModel.onAction(CalculatorAction.Select(targetItem.id))

        // When
        viewModel.onAction(CalculatorAction.Select(targetItem.id))

        // Then
        assertThat(viewModel.uiState.shoppingList.first().selected).isFalse()
        assertThat(viewModel.uiState.price).isEmpty()
        assertThat(viewModel.uiState.operation).isNull()
        assertThat(viewModel.uiState.quantity).isEmpty()
    }

    @Test
    fun testPerformSelection_withDifferentSelectedItem_shouldSelectTheSelectedItemAndDeselectOthers() {
        // Given
        // Item1
        viewModel.onAction(CalculatorAction.Number(1))
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Number(3))
        viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Add)

        // Item2
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Number(3))
        viewModel.onAction(CalculatorAction.Number(4))
        viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Add)

        // When
        val item1 = viewModel.uiState.shoppingList.first()
        val item2 = viewModel.uiState.shoppingList.last()
        viewModel.onAction(CalculatorAction.Select(item1.id))
        viewModel.onAction(CalculatorAction.Select(item2.id))

        // Then
        val newItem1 = viewModel.uiState.shoppingList.first()
        val newItem2 = viewModel.uiState.shoppingList.last()
        assertThat(newItem1.selected).isFalse()
        assertThat(newItem2.selected).isTrue()
    }
}