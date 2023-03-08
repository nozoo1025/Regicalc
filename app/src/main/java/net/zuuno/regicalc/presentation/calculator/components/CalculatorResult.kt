package net.zuuno.regicalc.presentation.calculator.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CalculatorResult(
    totalPrice: Double,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = "合計",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "(税込)",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.titleMedium
            )
        }
        Row {
            Text(
                text = "¥ ${"%.1f".format(totalPrice)}",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.displayMedium
            )
        }
    }
}