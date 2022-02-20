package com.example.osagocalculation.domain.entities

data class InsuranceDomain(
    val name: String,
    val rating: Double,
    val price: Int,
    val iconUrl: String?,
    val iconTitle: String,
    val fontColor: String,
    val iconBackground: String
)
