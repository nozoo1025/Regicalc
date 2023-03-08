package net.zuuno.regicalc.presentation.calculator.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material.icons.filled.PlaylistAdd
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

private sealed class CalculatorButtonContent {
    data class TextContent(val text: String) : CalculatorButtonContent()
    data class IconContent(val icon: ImageVector) : CalculatorButtonContent()
}

private data class Button(
    val content: CalculatorButtonContent,
    val onClick: () -> Unit,
    val buttonStyle: CalculatorButtonStyle = CalculatorButtonStyle.DefaultColor
)

@Composable
fun CalculatorKeypad(
    modifier: Modifier = Modifier
) {
    val calculatorButtons = listOf(
        listOf(
            Button(CalculatorButtonContent.TextContent("7"), { /*TODO*/ }),
            Button(CalculatorButtonContent.TextContent("8"), { /*TODO*/ }),
            Button(CalculatorButtonContent.TextContent("9"), { /*TODO*/ }),
            Button(
                content = CalculatorButtonContent.TextContent("AC"),
                onClick = { /*TODO*/ },
                buttonStyle = CalculatorButtonStyle.TertiaryColor
            )
        ),
        listOf(
            Button(CalculatorButtonContent.TextContent("4"), { /*TODO*/ }),
            Button(CalculatorButtonContent.TextContent("5"), { /*TODO*/ }),
            Button(CalculatorButtonContent.TextContent("6"), { /*TODO*/ }),
            Button(
                content = CalculatorButtonContent.IconContent(Icons.Default.Backspace),
                onClick = { /*TODO*/ },
                buttonStyle = CalculatorButtonStyle.SecondaryColor
            )
        ),
        listOf(
            Button(CalculatorButtonContent.TextContent("1"), { /*TODO*/ }),
            Button(CalculatorButtonContent.TextContent("2"), { /*TODO*/ }),
            Button(CalculatorButtonContent.TextContent("3"), { /*TODO*/ }),
            Button(
                content = CalculatorButtonContent.TextContent("×"),
                onClick = { /*TODO*/ },
                buttonStyle = CalculatorButtonStyle.SecondaryColor
            )
        ),
        listOf(
            Button(CalculatorButtonContent.TextContent("0"), { /*TODO*/ }),
            Button(CalculatorButtonContent.TextContent("00"), { /*TODO*/ }),
            Button(CalculatorButtonContent.TextContent("."), { /*TODO*/ }),
            Button(
                content = CalculatorButtonContent.IconContent(Icons.Default.PlaylistAdd),
                onClick = { /*TODO*/ },
                buttonStyle = CalculatorButtonStyle.PrimaryColor
            )
        )
    )

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        calculatorButtons.forEach { row ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                row.forEach { button ->
                    CalculatorButton(
                        content = { contentColor ->
                            when (button.content) {
                                is CalculatorButtonContent.TextContent -> {
                                    Text(
                                        text = button.content.text,
                                        color = contentColor,
                                        style = MaterialTheme.typography.headlineLarge
                                    )
                                }
                                is CalculatorButtonContent.IconContent -> {
                                    Icon(
                                        imageVector = button.content.icon,
                                        contentDescription = null,
                                        tint = contentColor,
                                        modifier = Modifier
                                            .size(32.dp)
                                    )
                                }
                            }
                        },
                        onClick = button.onClick,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f),
                        buttonStyle = button.buttonStyle
                    )
                }
            }
        }
    }
}