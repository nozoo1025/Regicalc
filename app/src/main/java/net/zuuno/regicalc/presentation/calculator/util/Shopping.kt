package net.zuuno.regicalc.presentation.calculator.util

import java.time.LocalDateTime

data class Shopping(
    val id: String,
    val price: Int,
    val quantity: Int,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val taxRate: TaxRate = TaxRate.Default,
    val selected: Boolean = false
)

fun Shopping.totalPrice(): Int {
    return price * quantity
}

fun List<Shopping>.calculateTotalPrice(): Int {
    return this
        .groupBy { it.taxRate }
        .map { (taxRate, shoppingList) ->
            (shoppingList.sumOf { it.totalPrice() } * (1 + taxRate.rate)).toInt()
        }
        .sum()
}