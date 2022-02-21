package com.example.osagocalculation.data.dto

import kotlinx.serialization.Serializable

@Serializable
class InsurancesResponse(
    val offers: List<InsuranceData>
)
