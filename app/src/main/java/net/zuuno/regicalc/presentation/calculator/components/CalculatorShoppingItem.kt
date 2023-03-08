package net.zuuno.regicalc.presentation.calculator.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import net.zuuno.regicalc.presentation.calculator.util.CalculatorOperation
import net.zuuno.regicalc.presentation.calculator.util.Shopping

@Composable
fun CalculatorShoppingItem(
    shopping: Shopping,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .clickable { /*TODO*/ }
            .background(
                color = MaterialTheme.colorScheme.surface,
            )
            .then(modifier),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            // TODO: Calculator Chip
        }
        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = "%.1f".format(shopping.price),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = CalculatorOperation.Multiply.symbol,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = shopping.quantity.toString(),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}