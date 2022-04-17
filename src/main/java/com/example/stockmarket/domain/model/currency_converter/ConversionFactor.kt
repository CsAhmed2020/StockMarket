package com.example.stockmarket.domain.model.currency_converter

import kotlinx.datetime.Instant

data class ConversionFactor(
    val baseCurrency: Currency,
    val targetCurrency: Currency,
    val rate: Double,
    val lastUpdate: Instant
)
