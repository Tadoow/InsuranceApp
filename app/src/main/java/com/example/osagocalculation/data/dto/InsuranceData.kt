package com.example.osagocalculation.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class InsuranceData(
    val name: String,
    val rating: Double,
    val price: Int,
    val branding: BrandData
)
