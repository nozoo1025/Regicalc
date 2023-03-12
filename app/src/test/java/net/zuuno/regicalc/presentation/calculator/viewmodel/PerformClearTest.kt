package net.zuuno.regicalc.presentation.calculator.viewmodel

import com.google.common.truth.Truth.assertThat
import net.zuuno.regicalc.presentation.calculator.CalculatorUiState
import net.zuuno.regicalc.presentation.calculator.CalculatorViewModel
import net.zuuno.regicalc.presentation.calculator.util.CalculatorAction
import org.junit.Before
import org.junit.Test

class PerformClearTest {

    private lateinit var viewModel: CalculatorViewModel

    @Before
    fun setUp() {
        viewModel = CalculatorViewModel()
    }

    @Test
    fun testPerformClear_deletedUiStateShouldBeNull() {
        assertThat(viewModel.deletedUiState).isNull()
    }

    @Test
    fun testPerformClear_withDefaultState_shouldNotChangeState() {
        // When
        viewModel.onAction(CalculatorAction.Clear)

        // Then
        assertThat(viewModel.uiState).isEqualTo(CalculatorUiState())
    }

    @Test
    fun testPerformClear_withNonDefaultState_shouldClearState() {
        // Given
        viewModel.onAction(CalculatorAction.Number(1))
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Number(3))
        viewModel.onAction(CalculatorAction.Add)

        // When
        viewModel.onAction(CalculatorAction.Clear)

        // Then
        assertThat(viewModel.uiState).isEqualTo(CalculatorUiState())
    }

    @Test
    fun testPerformClear_withClearedState_shouldSaveDeletedState() {
        // Given
        viewModel.onAction(CalculatorAction.Number(1))
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Number(3))
        viewModel.onAction(CalculatorAction.Add)

        // When
        val currentStateBeforeClear = viewModel.uiState
        viewModel.onAction(CalculatorAction.Clear)

        // Then
        assertThat(viewModel.deletedUiState).isEqualTo(currentStateBeforeClear)
    }
}