package net.zuuno.regicalc.presentation.calculator.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.zuuno.regicalc.presentation.calculator.util.CalculatorAction
import net.zuuno.regicalc.presentation.calculator.util.Shopping

@Composable
fun CalculatorShoppingList(
    shoppingList: List<Shopping>,
    onAction: (CalculatorAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val lazyListState = rememberLazyListState()

    LazyColumn(
        modifier = modifier,
        reverseLayout = true,
        state = lazyListState
    ) {
        items(
            items = shoppingList,
            key = { it.id }
        ) { shopping ->
            CalculatorShoppingItem(
                shopping = shopping,
                onAction = onAction,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 8.dp
                    )
            )
        }
    }

    LaunchedEffect(shoppingList.size) {
        lazyListState.animateScrollToItem(0)
    }
}