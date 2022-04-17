package com.example.stockmarket.data.remote.dto

import com.example.stockmarket.domain.model.currency_converter.Currency
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SupportedCodes(
    val documentation: String,
    val result: String,
    @SerialName("supported_codes")
    val supportedCodes: List<List<String>>,
    @SerialName("terms_of_use")
    val termsOfUse: String
)

fun SupportedCodes.toDomain() = supportedCodes.map {
    Currency(
        it.first(),
        it.last()
    )
}
