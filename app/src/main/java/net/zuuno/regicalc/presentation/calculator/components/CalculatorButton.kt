package net.zuuno.regicalc.presentation.calculator.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

sealed class CalculatorButtonStyle {
    object DefaultColor : CalculatorButtonStyle()
    object PrimaryColor : CalculatorButtonStyle()
    object SecondaryColor : CalculatorButtonStyle()
    object TertiaryColor : CalculatorButtonStyle()
}

@Composable
fun CalculatorButton(
    content: @Composable (Color) -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonStyle: CalculatorButtonStyle = CalculatorButtonStyle.DefaultColor
) {
    val containerColor = when (buttonStyle) {
        is CalculatorButtonStyle.DefaultColor -> MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp)
        is CalculatorButtonStyle.PrimaryColor -> MaterialTheme.colorScheme.primaryContainer
        is CalculatorButtonStyle.SecondaryColor -> MaterialTheme.colorScheme.secondaryContainer
        is CalculatorButtonStyle.TertiaryColor -> MaterialTheme.colorScheme.tertiaryContainer
    }

    val contentColor = when (buttonStyle) {
        is CalculatorButtonStyle.DefaultColor -> MaterialTheme.colorScheme.onSurface
        is CalculatorButtonStyle.PrimaryColor -> MaterialTheme.colorScheme.onPrimaryContainer
        is CalculatorButtonStyle.SecondaryColor -> MaterialTheme.colorScheme.onSecondaryContainer
        is CalculatorButtonStyle.TertiaryColor -> MaterialTheme.colorScheme.onTertiaryContainer
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(CircleShape)
            .background(containerColor)
            .clickable { onClick() }
            .then(modifier)
    ) {
        content(contentColor)
    }
}