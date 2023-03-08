package net.zuuno.regicalc.presentation.calculator.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.zuuno.regicalc.presentation.calculator.util.Shopping

@Composable
fun CalculatorShoppingList(
    shoppingList: List<Shopping>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        reverseLayout = true
    ) {
        items(
            items = shoppingList,
            key = { it.id }
        ) { shopping ->
            CalculatorShoppingItem(
                shopping = shopping,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 8.dp
                    )
            )
        }
    }
}