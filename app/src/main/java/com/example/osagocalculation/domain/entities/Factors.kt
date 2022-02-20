package com.example.osagocalculation.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class Factors : Parcelable {
    @Parcelize
    data class Header(
        val headerValues: List<String>,
        var expanded: Boolean = false
    ) : Factors()

    @Parcelize
    data class FactorDomain(
        val title: String,
        val headerValue: String,
        val value: String,
        val name: String,
        val detailText: String
    ) : Factors()
}
