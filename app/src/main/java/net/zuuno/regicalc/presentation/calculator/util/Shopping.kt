package net.zuuno.regicalc.presentation.calculator.util

import java.time.LocalDateTime

data class Shopping(
    val id: String,
    val price: Int,
    val quantity: Int,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val taxRate: TaxRate = TaxRate.Default
)

fun Shopping.totalPrice(): Int {
    return( price * quantity * (1 + taxRate.rate)).toInt()
}
