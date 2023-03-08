package net.zuuno.regicalc.presentation.calculator.util

sealed class TaxRate(val rate: Double) {
    object Default : TaxRate(0.1)
    object Eight : TaxRate(0.08)
    object None : TaxRate(0.0)
}