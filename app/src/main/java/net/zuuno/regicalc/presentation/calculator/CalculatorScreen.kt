package net.zuuno.regicalc.presentation.calculator

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import net.zuuno.regicalc.presentation.calculator.components.CalculatorDisplay
import net.zuuno.regicalc.presentation.calculator.components.CalculatorKeypad
import net.zuuno.regicalc.presentation.calculator.components.CalculatorResult
import net.zuuno.regicalc.presentation.calculator.components.CalculatorShoppingList
import net.zuuno.regicalc.presentation.calculator.util.CalculatorAction

@Composable
fun CalculatorScreen() {
    val viewModel = viewModel<CalculatorViewModel>()
    val snackbarHost = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHost) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
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
                        when (action) {
                            is CalculatorAction.Clear -> {
                                viewModel.onAction(CalculatorAction.Clear)

                                if (viewModel.deletedUiState?.shoppingList.isNullOrEmpty()) {
                                    return@CalculatorKeypad
                                }

                                scope.launch {
                                    val result = snackbarHost.showSnackbar(
                                        message = "全件削除しました",
                                        actionLabel = "元に戻す",
                                        duration = SnackbarDuration.Short
                                    )

                                    if (result == SnackbarResult.ActionPerformed) {
                                        viewModel.onAction(CalculatorAction.Undo)
                                    }
                                }
                            }
                            else -> {
                                viewModel.onAction(action)
                            }
                        }
                    }
                )
            }
        }
    }
}