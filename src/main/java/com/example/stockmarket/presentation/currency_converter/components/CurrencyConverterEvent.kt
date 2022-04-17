package com.example.stockmarket.presentation.currency_converter.components

import com.example.stockmarket.domain.model.currency_converter.Currency


sealed class CurrencyConverterEvent{
    object EvaluatePressed: CurrencyConverterEvent()
    object SwitchCurrenciesPressed: CurrencyConverterEvent()
    object BaseCurrencyChangeRequested: CurrencyConverterEvent()
    object TargetCurrencyChangeRequested: CurrencyConverterEvent()
    object PullToRefresh: CurrencyConverterEvent()
    object OnStop: CurrencyConverterEvent()
    data class SearchDisplayTextChanged(val newText: String): CurrencyConverterEvent()
    data class BaseCurrencyDisplayTextChanged(val newText: String): CurrencyConverterEvent()
    data class CurrencySelected(val currency: Currency): CurrencyConverterEvent()
}
