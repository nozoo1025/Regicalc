package net.zuuno.regicalc.presentation.calculator.viewmodel

import com.google.common.truth.Truth
import net.zuuno.regicalc.presentation.calculator.CalculatorUiState
import net.zuuno.regicalc.presentation.calculator.CalculatorViewModel
import net.zuuno.regicalc.presentation.calculator.util.CalculatorAction
import net.zuuno.regicalc.presentation.calculator.util.TaxRate
import org.junit.Before
import org.junit.Test

class PerformAdditionTest {

    private lateinit var viewModel: CalculatorViewModel

    @Before
    fun setUp() {
        viewModel = CalculatorViewModel()
    }

    @Test
    fun testPerformAddition_withBlankPrice_shouldNotChangeUiState() {
        // When
        viewModel.onAction(CalculatorAction.Add)

        // Then
        Truth.assertThat(viewModel.uiState).isEqualTo(CalculatorUiState())
    }

    @Test
    fun testPerformAddition_withNoSelectedShoppingItem_shouldCreateNewShoppingItem() {
        // When
        viewModel.onAction(CalculatorAction.Number(1))
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Number(3))
        viewModel.onAction(CalculatorAction.Add)

        // Then
        val addedItem = viewModel.uiState.shoppingList.first()
        Truth.assertThat(viewModel.uiState.shoppingList).hasSize(1)
        Truth.assertThat(addedItem.price).isEqualTo(123)
        Truth.assertThat(addedItem.quantity).isEqualTo(1)
    }

    @Test
    fun testPerformAddition_withSelectedShoppingItem_shouldUpdatedSelectedShoppingItem() {
        // Given
        viewModel.onAction(CalculatorAction.Number(1))
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Number(3))
        viewModel.onAction(CalculatorAction.Add)

        // When
        val targetItem = viewModel.uiState.shoppingList.first()
        viewModel.onAction(CalculatorAction.Select(targetItem.id))
        viewModel.onAction(CalculatorAction.Delete)
        viewModel.onAction(CalculatorAction.Number(3))
        viewModel.onAction(CalculatorAction.Add)

        // Then
        val editedItem = viewModel.uiState.shoppingList.find { it.id == targetItem.id }!!
        Truth.assertThat(viewModel.uiState.shoppingList).hasSize(1)
        Truth.assertThat(editedItem.price).isEqualTo(123)
        Truth.assertThat(editedItem.quantity).isEqualTo(3)
    }

    @Test
    fun testPerformAddition_withFirstItem_shouldSetDefaultTaxRate() {
        // When
        viewModel.onAction(CalculatorAction.Number(1))
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Number(3))
        viewModel.onAction(CalculatorAction.Add)

        // Then
        val addedItem = viewModel.uiState.shoppingList.first()
        Truth.assertThat(addedItem.taxRate).isEqualTo(TaxRate.Default)
    }

    @Test
    fun testPerformAddition_withSecondAndLaterItem_shouldSetSameTaxRateAsPreviousItem() {
        // Given
        viewModel.onAction(CalculatorAction.Number(1))
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Number(3))
        viewModel.onAction(CalculatorAction.Add)

        val targetItem = viewModel.uiState.shoppingList.first()
        viewModel.onAction(CalculatorAction.SetTaxRate(TaxRate.Eight, targetItem.id))

        // When
        viewModel.onAction(CalculatorAction.Number(1))
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Number(3))
        viewModel.onAction(CalculatorAction.Add)

        // Then
        val addedItem = viewModel.uiState.shoppingList.last()
        Truth.assertThat(addedItem.taxRate).isEqualTo(TaxRate.Eight)
    }
}