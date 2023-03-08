package net.zuuno.regicalc.presentation.calculator

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import net.zuuno.regicalc.presentation.calculator.components.CalculatorDisplay
import net.zuuno.regicalc.presentation.calculator.components.CalculatorKeypad
import net.zuuno.regicalc.presentation.calculator.components.CalculatorResult
import net.zuuno.regicalc.presentation.calculator.components.CalculatorShoppingList

@Composable
fun CalculatorScreen() {
    val viewModel = viewModel<CalculatorViewModel>()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CalculatorResult(
            totalPrice = viewModel.uiState.totalPrice,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp
                )
        )
        CalculatorShoppingList(
            shoppingList = viewModel.uiState.shoppingList,
            onAction = { action ->
                viewModel.onAction(action)
            },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp
                ),
            verticalArrangement = Arrangement.Bottom
        ) {
            CalculatorDisplay(
                modifier = Modifier.fillMaxWidth(),
                price = viewModel.uiState.price,
                quantity = viewModel.uiState.quantity,
                operation = viewModel.uiState.operation
            )
            CalculatorKeypad(
                modifier = Modifier.fillMaxWidth(),
                onAction = { action ->
                    viewModel.onAction(action)
                }
            )
        }
    }
}