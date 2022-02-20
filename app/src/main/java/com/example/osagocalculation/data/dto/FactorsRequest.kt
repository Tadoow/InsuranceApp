package com.example.osagocalculation.data.dto

import kotlinx.serialization.Serializable

@Serializable
class FactorsRequest(
    val factors: List<FactorData>
)
