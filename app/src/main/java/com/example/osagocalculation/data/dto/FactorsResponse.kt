package com.example.osagocalculation.data.dto

import kotlinx.serialization.Serializable

@Serializable
class FactorsResponse(
    val factors: List<FactorData>
)
