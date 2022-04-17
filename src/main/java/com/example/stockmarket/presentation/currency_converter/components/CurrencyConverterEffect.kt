package com.example.stockmarket.presentation.currency_converter.components

sealed class CurrencyConverterEffect{
    data class ShowToast(val message: String): CurrencyConverterEffect()
}
