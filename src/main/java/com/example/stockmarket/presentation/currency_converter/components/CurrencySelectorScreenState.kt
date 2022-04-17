package com.example.stockmarket.presentation.currency_converter.components

import com.example.stockmarket.domain.model.currency_converter.Currency
import kotlinx.coroutines.flow.StateFlow

interface CurrencySelectorScreenState {
    val currencyList: StateFlow<List<Currency>>
    val searchDisplay: StateFlow<String>
    val isLoading: StateFlow<Boolean>
    val pullToRefreshVisible: StateFlow<Boolean>
    val error: StateFlow<String?>
}