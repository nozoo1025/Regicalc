package net.zuuno.regicalc.presentation.calculator.viewmodel

import com.google.common.truth.Truth.assertThat
import net.zuuno.regicalc.presentation.calculator.CalculatorViewModel
import net.zuuno.regicalc.presentation.calculator.util.CalculatorAction
import net.zuuno.regicalc.presentation.calculator.util.CalculatorOperation
import net.zuuno.regicalc.presentation.calculator.util.TaxRate
import org.junit.Before
import org.junit.Test

class TestSetTaxRate {

    private lateinit var viewModel: CalculatorViewModel

    @Before
    fun setUp() {
        viewModel = CalculatorViewModel()
    }

    @Test
    fun testSetTaxRate_withExistingItem_shouldUpdateTaxRate() {
        // Given
        viewModel.onAction(CalculatorAction.Number(1))
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Number(3))
        viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Add)

        // When
        val targetItem = viewModel.uiState.shoppingList.first()
        viewModel.onAction(CalculatorAction.SetTaxRate(TaxRate.Eight, targetItem.id))

        // Then
        assertThat(viewModel.uiState.shoppingList.first().taxRate).isEqualTo(TaxRate.Eight)
    }

    @Test
    fun testSetTaxRate_withSameTaxRate_shouldSetNone() {
        // Given
        viewModel.onAction(CalculatorAction.Number(1))
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Number(3))
        viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Add)

        // When
        val targetItem = viewModel.uiState.shoppingList.first()
        viewModel.onAction(CalculatorAction.SetTaxRate(TaxRate.Default, targetItem.id))

        // Then
        assertThat(viewModel.uiState.shoppingList.first().taxRate).isEqualTo(TaxRate.None)
    }
}