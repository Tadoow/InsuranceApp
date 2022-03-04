package com.example.osagocalculation.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BrandData(
    val fontColor: String,
    val backgroundColor: String,
    val iconTitle: String,
    @SerialName("bankLogoUrlSVG")
    val iconUrl: String? = null
)
