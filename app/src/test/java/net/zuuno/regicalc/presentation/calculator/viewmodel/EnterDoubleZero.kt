package net.zuuno.regicalc.presentation.calculator.viewmodel

import com.google.common.truth.Truth
import net.zuuno.regicalc.presentation.calculator.CalculatorUiState
import net.zuuno.regicalc.presentation.calculator.CalculatorViewModel
import net.zuuno.regicalc.presentation.calculator.util.CalculatorAction
import net.zuuno.regicalc.presentation.calculator.util.CalculatorOperation
import org.junit.Before
import org.junit.Test

class EnterDoubleZero {

    private lateinit var viewModel: CalculatorViewModel

    @Before
    fun setUp() {
        viewModel = CalculatorViewModel()
    }

    @Test
    fun testInitialUiState_shouldBeEqualToDefaultState() {
        val expectedState = CalculatorUiState()

        Truth.assertThat(expectedState).isEqualTo(viewModel.uiState)
    }

    @Test
    fun testEnterDoubleZero_withNumberOnly_shouldAddDoubleZeroToPrice() {
        // When
        viewModel.onAction(CalculatorAction.DoubleZero)

        // Then
        Truth.assertThat(viewModel.uiState.price).isEqualTo("00")
    }

    @Test
    fun testEnterDoubleZero_withNumberAndMultiply_shouldAddDoubleZeroToPrice() {
        // When
        viewModel.onAction(CalculatorAction.Number(1))
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Number(3))
        viewModel.onAction(CalculatorAction.DoubleZero)
        viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))

        // Then
        Truth.assertThat(viewModel.uiState.price).isEqualTo("12300")
    }

    @Test
    fun testEnterDoubleZero_withMaxLimit_shouldTruncateInput() {
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
        viewModel.onAction(CalculatorAction.DoubleZero)

        // Then
        Truth.assertThat(viewModel.uiState.price).isEqualTo("12345678")
    }

    @Test
    fun testEnterDoubleZero_withNumberAndMultiplyAndNumber_shouldAddDoubleZeroToQuantity() {
        // When
        viewModel.onAction(CalculatorAction.Number(1))
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Number(3))
        viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Number(3))
        viewModel.onAction(CalculatorAction.Number(4))
        viewModel.onAction(CalculatorAction.DoubleZero)

        // Then
        Truth.assertThat(viewModel.uiState.price).isEqualTo("123")
        Truth.assertThat(viewModel.uiState.quantity).isEqualTo("23400")
    }

    @Test
    fun testEnterDoubleZero_withNumberAndMultiplyAndNumberAndMaxLimit_shouldTruncateInput() {
        // Given
        viewModel.onAction(CalculatorAction.Number(1))
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Number(3))
        viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))
        viewModel.onAction(CalculatorAction.Number(1))
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Number(3))
        viewModel.onAction(CalculatorAction.Number(4))
        viewModel.onAction(CalculatorAction.Number(5))
        viewModel.onAction(CalculatorAction.Number(6))
        viewModel.onAction(CalculatorAction.Number(7))
        viewModel.onAction(CalculatorAction.Number(8))

        // When
        viewModel.onAction(CalculatorAction.DoubleZero)

        // Then
        Truth.assertThat(viewModel.uiState.price).isEqualTo("123")
        Truth.assertThat(viewModel.uiState.quantity).isEqualTo("12345678")
    }

    @Test
    fun testEnterDoubleZero_withMaxLimitAndMultiply_shouldTruncateInput() {
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
        viewModel.onAction(CalculatorAction.DoubleZero)

        // Then
        Truth.assertThat(viewModel.uiState.price).isEqualTo("12345678")
        Truth.assertThat(viewModel.uiState.operation).isEqualTo(CalculatorOperation.Multiply)
        Truth.assertThat(viewModel.uiState.quantity).isEqualTo("00")
    }
}