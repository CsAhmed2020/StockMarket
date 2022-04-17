package com.example.stockmarket.presentation.companies

import com.example.stockmarket.domain.model.CompanyListing

data class CompaniesState (
    val companies: List<CompanyListing> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
        )