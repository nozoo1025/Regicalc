package net.zuuno.regicalc.presentation.calculator.viewmodel

import com.google.common.truth.Truth.assertThat
import net.zuuno.regicalc.presentation.calculator.CalculatorViewModel
import net.zuuno.regicalc.presentation.calculator.util.CalculatorAction
import net.zuuno.regicalc.presentation.calculator.util.CalculatorOperation
import org.junit.Before
import org.junit.Test

class PerformUndoTest {

    private lateinit var viewModel: CalculatorViewModel

    @Before
    fun setUp() {
        viewModel = CalculatorViewModel()
    }

    @Test
    fun testPerformUndo_withDeletedUiState_shouldReturnToPreviousUiState() {
        // Given
        viewModel.onAction(CalculatorAction.Number(1))
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Number(3))
        viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Add)
        val expectedState = viewModel.uiState

        // When
        viewModel.onAction(CalculatorAction.Delete)
        viewModel.onAction(CalculatorAction.Undo)

        // Then
        assertThat(viewModel.uiState).isEqualTo(expectedState)
    }
}