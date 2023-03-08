package net.zuuno.regicalc.presentation.calculator.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import net.zuuno.regicalc.presentation.calculator.util.TaxRate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaxRateChip(
    taxRate: TaxRate,
    onClick: () -> Unit,
    selected: Boolean = false
) {
    FilterChip(
        selected = selected,
        onClick = onClick,
        label = {
            Text(text = "${(taxRate.rate * 100).toInt()}%")
        },
        leadingIcon = {
            if (selected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Selected"
                )
            }
        }
    )
}