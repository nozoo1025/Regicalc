package net.zuuno.regicalc.presentation.calculator.viewmodel

import com.google.common.truth.Truth.assertThat
import net.zuuno.regicalc.presentation.calculator.CalculatorUiState
import net.zuuno.regicalc.presentation.calculator.CalculatorViewModel
import net.zuuno.regicalc.presentation.calculator.util.CalculatorAction
import net.zuuno.regicalc.presentation.calculator.util.CalculatorOperation
import org.junit.Before
import org.junit.Test

class EnterNumber {

    private lateinit var viewModel: CalculatorViewModel

    @Before
    fun setUp() {
        viewModel = CalculatorViewModel()
    }

    @Test
    fun testInitialUiState_shouldBeEqualToDefaultState() {
        val expectedState = CalculatorUiState()

        assertThat(expectedState).isEqualTo(viewModel.uiState)
    }

    @Test
    fun testEnterNumber_withNumberOnly_shouldAddNumberToPrice() {
        // When
        viewModel.onAction(CalculatorAction.Number(1))
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Number(3))

        // Then
        assertThat(viewModel.uiState.price).isEqualTo("123")
    }

    @Test
    fun testEnterNumber_withNumberAndMultiply_shouldAddNumberToPrice() {
        // When
        viewModel.onAction(CalculatorAction.Number(1))
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Number(3))
        viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))

        // Then
        assertThat(viewModel.uiState.price).isEqualTo("123")
    }

    @Test
    fun testEnterNumber_withNumberAndMultiplyAndNumber_shouldAddNumberToPrice() {
        // When
        viewModel.onAction(CalculatorAction.Number(1))
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Number(3))
        viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Number(3))
        viewModel.onAction(CalculatorAction.Number(4))

        // Then
        assertThat(viewModel.uiState.price).isEqualTo("123")
        assertThat(viewModel.uiState.quantity).isEqualTo("234")
    }

    @Test
    fun testEnterNumber_withMaxLimit_shouldTruncateInput() {
        // Given
        viewModel.onAction(CalculatorAction.Number(1))
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Number(3))
        viewModel.onAction(CalculatorAction.Number(4))
        viewModel.onAction(CalculatorAction.Number(5))
        viewModel.onAction(CalculatorAction.Number(6))
        viewModel.onAction(CalculatorAction.Number(7))
        viewModel.onAction(CalculatorAction.Number(8))

        // When
        viewModel.onAction(CalculatorAction.Number(9))

        // Then
        assertThat(viewModel.uiState.price).isEqualTo("12345678")
    }

    @Test
    fun testEnterNumber_withMaxLimitAndMultiply_shouldTruncateInput() {
        // Given
        viewModel.onAction(CalculatorAction.Number(1))
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Number(3))
        viewModel.onAction(CalculatorAction.Number(4))
        viewModel.onAction(CalculatorAction.Number(5))
        viewModel.onAction(CalculatorAction.Number(6))
        viewModel.onAction(CalculatorAction.Number(7))
        viewModel.onAction(CalculatorAction.Number(8))
        viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))

        // When
        viewModel.onAction(CalculatorAction.Number(9))

        // Then
        assertThat(viewModel.uiState.price).isEqualTo("12345678")
    }
}