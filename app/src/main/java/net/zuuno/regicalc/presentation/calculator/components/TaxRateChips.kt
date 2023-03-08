package net.zuuno.regicalc.presentation.calculator.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.zuuno.regicalc.presentation.calculator.util.CalculatorAction
import net.zuuno.regicalc.presentation.calculator.util.Shopping
import net.zuuno.regicalc.presentation.calculator.util.TaxRate

@Composable
fun TaxRateChips(
    shopping: Shopping,
    onAction: (CalculatorAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TaxRateChip(
            taxRate = TaxRate.Default,
            onClick = {
                onAction(
                    CalculatorAction.SetTaxRate(
                        shoppingId = shopping.id,
                        taxRate = TaxRate.Default
                    )
                )
            },
            selected = shopping.taxRate == TaxRate.Default
        )
        TaxRateChip(
            taxRate = TaxRate.Eight,
            onClick = {
                onAction(
                    CalculatorAction.SetTaxRate(
                        shoppingId = shopping.id,
                        taxRate = TaxRate.Eight
                    )
                )
            },
            selected = shopping.taxRate == TaxRate.Eight
        )
    }
}