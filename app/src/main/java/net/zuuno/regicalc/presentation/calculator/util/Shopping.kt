package net.zuuno.regicalc.presentation.calculator.util

import java.time.LocalDateTime

data class Shopping(
    val id: String,
    val price: Double,
    val quantity: Int,
    val createdAt: LocalDateTime
)

fun Shopping.totalPrice(): Double {
    return price * quantity
}
