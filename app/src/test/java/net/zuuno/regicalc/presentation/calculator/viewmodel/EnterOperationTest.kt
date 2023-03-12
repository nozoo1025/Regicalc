package net.zuuno.regicalc.presentation.calculator.viewmodel

import com.google.common.truth.Truth
import net.zuuno.regicalc.presentation.calculator.CalculatorUiState
import net.zuuno.regicalc.presentation.calculator.CalculatorViewModel
import net.zuuno.regicalc.presentation.calculator.util.CalculatorAction
import net.zuuno.regicalc.presentation.calculator.util.CalculatorOperation
import org.junit.Before
import org.junit.Test

class EnterOperationTest {

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
    fun testEnterOperation_withBlankPrice_shouldNotEnterOperation() {
        // When
        viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))

        // Then
        Truth.assertThat(viewModel.uiState.price).isEqualTo("")
        Truth.assertThat(viewModel.uiState.operation).isNull()
    }

    @Test
    fun testEnterOperation_withNonBlankPrice_shouldEnterOperation() {
        // When
        viewModel.onAction(CalculatorAction.Number(1))
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Number(3))
        viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))

        // Then
        Truth.assertThat(viewModel.uiState.price).isEqualTo("123")
        Truth.assertThat(viewModel.uiState.operation).isEqualTo(CalculatorOperation.Multiply)
    }
}