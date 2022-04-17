package com.example.stockmarket.presentation.companies

sealed class CompaniesEvent{
    object Refresh: CompaniesEvent()
    data class OnSearchQueryChange(val query: String): CompaniesEvent()
}
