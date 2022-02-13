package com.example.osagocalculation.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class FactorData(
    val title: String,
    val headerValue: String,
    var value: String,
    val name: String,
    val detailText: String
)
