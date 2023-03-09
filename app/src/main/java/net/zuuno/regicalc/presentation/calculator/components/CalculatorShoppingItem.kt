package net.zuuno.regicalc.presentation.calculator.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.zuuno.regicalc.presentation.calculator.util.CalculatorAction
import net.zuuno.regicalc.presentation.calculator.util.CalculatorOperation
import net.zuuno.regicalc.presentation.calculator.util.Shopping
import net.zuuno.regicalc.presentation.calculator.util.totalPrice

@Composable
fun CalculatorShoppingItem(
    shopping: Shopping,
    onAction: (CalculatorAction) -> Unit,
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
            TaxRateChips(
                shopping = shopping,
                onAction = onAction
            )
        }
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "${shopping.price}",
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
            Text(
                text = "= ${shopping.totalPrice()}",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}