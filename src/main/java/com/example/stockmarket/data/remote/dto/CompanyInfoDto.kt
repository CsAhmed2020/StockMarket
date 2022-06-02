package com.example.stockmarket.data.remote.dto

import com.squareup.moshi.Json

data class CompanyInfoDto(
    @field:Json(name = "Symbol") val symbol: String?,
    @field:Json(name = "Description") val description: String?,
    @field:Json(name = "Name") val name: String?,
    @field:Json(name = "Country") val country: String?,
    @field:Json(name = "Industry") val industry: String?,
    @field:Json(name = "PERatio") val peRatio:String?,
    @field:Json(name = "PEGRatio") val pegRatio:String?,
    @field:Json(name = "EPS") val eps:String?
)
