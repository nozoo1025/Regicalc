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
import net.zuuno.regicalc.presentation.calculator.util.CalculatorOperation

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
    modifier: Modifier = Modifier,
    onAction: (CalculatorAction) -> Unit
) {
    val calculatorButtons = listOf(
        listOf(
            Button(
                content = CalculatorButtonContent.TextContent("7"),
                onClick = {
                    onAction(CalculatorAction.Number(7))
                }
            ),
            Button(
                content = CalculatorButtonContent.TextContent("8"),
                onClick = {
                    onAction(CalculatorAction.Number(8))
                }
            ),
            Button(
                content = CalculatorButtonContent.TextContent("9"),
                onClick = {
                    onAction(CalculatorAction.Number(9))
                }
            ),
            Button(
                content = CalculatorButtonContent.TextContent("AC"),
                onClick = { /*TODO*/ },
                buttonStyle = CalculatorButtonStyle.TertiaryColor
            )
        ),
        listOf(
            Button(
                content = CalculatorButtonContent.TextContent("4"),
                onClick = {
                    onAction(CalculatorAction.Number(4))
                }
            ),
            Button(
                content = CalculatorButtonContent.TextContent("5"),
                onClick = {
                    onAction(CalculatorAction.Number(5))
                }
            ),
            Button(
                content = CalculatorButtonContent.TextContent("6"),
                onClick = {
                    onAction(CalculatorAction.Number(6))
                }
            ),
            Button(
                content = CalculatorButtonContent.IconContent(Icons.Default.Backspace),
                onClick = { /*TODO*/ },
                buttonStyle = CalculatorButtonStyle.SecondaryColor
            )
        ),
        listOf(
            Button(
                content = CalculatorButtonContent.TextContent("1"),
                onClick = {
                    onAction(CalculatorAction.Number(1))
                }
            ),
            Button(
                content = CalculatorButtonContent.TextContent("2"),
                onClick = {
                    onAction(CalculatorAction.Number(2))
                }
            ),
            Button(
                content = CalculatorButtonContent.TextContent("3"),
                onClick = {
                    onAction(CalculatorAction.Number(3))
                }
            ),
            Button(
                content = CalculatorButtonContent.TextContent("×"),
                onClick = {
                          onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))
                },
                buttonStyle = CalculatorButtonStyle.SecondaryColor
            )
        ),
        listOf(
            Button(
                content = CalculatorButtonContent.TextContent("0"),
                onClick = {
                    onAction(CalculatorAction.Number(0))
                }
            ),
            Button(
                content = CalculatorButtonContent.TextContent("00"),
                onClick = {
                    onAction(CalculatorAction.DoubleZero)
                }
            ),
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