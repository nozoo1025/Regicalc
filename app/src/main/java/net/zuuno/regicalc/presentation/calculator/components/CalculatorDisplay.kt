package net.zuuno.regicalc.presentation.calculator.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import net.zuuno.regicalc.presentation.calculator.util.CalculatorOperation

@Composable
fun CalculatorDisplay(
    modifier: Modifier = Modifier,
    price: String = "",
    quantity: String = "",
    operation: CalculatorOperation? = null
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterEnd
    ) {
        Text(
            text = price + (operation?.symbol ?: "") + quantity,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.displayLarge
        )
    }
}