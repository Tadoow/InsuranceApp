package com.example.osagocalculation.domain.entities

sealed class Factors {
    class Header(
        val headerValues: List<String>,
        var expanded: Boolean = false
    ) : Factors()

    data class FactorDomain(
        val title: String,
        val headerValue: String,
        val value: String,
        val name: String,
        val detailText: String
    ) : Factors()
}
