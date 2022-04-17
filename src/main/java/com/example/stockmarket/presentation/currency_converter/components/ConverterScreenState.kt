package com.example.stockmarket.presentation.currency_converter.components

import com.example.stockmarket.domain.model.currency_converter.Currency
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface ConverterScreenState {
    val baseCurrency: StateFlow<Currency>
    val targetCurrency: StateFlow<Currency>
    val baseCurrencyDisplay: StateFlow<String>
    val targetCurrencyDisplay: StateFlow<String>
    val isLoading: StateFlow<Boolean>
    val error: StateFlow<String?>
    val effectStream: Flow<CurrencyConverterEffect>
}