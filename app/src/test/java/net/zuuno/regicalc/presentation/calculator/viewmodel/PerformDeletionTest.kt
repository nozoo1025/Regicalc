package net.zuuno.regicalc.presentation.calculator.viewmodel

import com.google.common.truth.Truth.assertThat
import net.zuuno.regicalc.presentation.calculator.CalculatorViewModel
import net.zuuno.regicalc.presentation.calculator.util.CalculatorAction
import net.zuuno.regicalc.presentation.calculator.util.CalculatorOperation
import org.junit.Before
import org.junit.Test

class PerformDeletionTest {

    private lateinit var viewModel: CalculatorViewModel

    @Before
    fun setUp() {
        viewModel = CalculatorViewModel()
    }

    @Test
    fun testPerformDeletion_withNonEmptyQuantity_shouldRemoveLastCharacterFromQuantity() {
        // Given
        viewModel.onAction(CalculatorAction.Number(1))
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Number(3))
        viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))
        viewModel.onAction(CalculatorAction.Number(1))
        viewModel.onAction(CalculatorAction.Number(5))

        // When
        viewModel.onAction(CalculatorAction.Delete)

        // Then
        assertThat(viewModel.uiState.quantity).isEqualTo("1")
    }

    @Test
    fun testPerformDeletion_withNonNullOperator_shouldRemoveOperation() {
        // Given
        viewModel.onAction(CalculatorAction.Number(1))
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Number(3))
        viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))

        // When
        viewModel.onAction(CalculatorAction.Delete)

        // Then
        assertThat(viewModel.uiState.operation).isNull()
    }

    @Test
    fun testPerformDeletion_withNonEmptyPrice_shouldRemoveLastCharacterFromPrice() {
        // Given
        viewModel.onAction(CalculatorAction.Number(1))
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Number(3))

        // When
        viewModel.onAction(CalculatorAction.Delete)

        // Then
        assertThat(viewModel.uiState.price).isEqualTo("12")
    }

    @Test
    fun testPerformDeletion_withAllFieldsEmpty_shouldNotChangeUiState() {
        // Given
        val expectedState = viewModel.uiState

        // When
        viewModel.onAction(CalculatorAction.Delete)

        // Then
        assertThat(viewModel.uiState).isEqualTo(expectedState)
    }
}