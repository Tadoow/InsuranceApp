package com.example.osagocalculation.domain.entities

sealed class Coefficients {
    class Header(
        val coefficients: List<Coefficient>,
        var expanded: Boolean = false
    ) : Coefficients()

    class Coefficient(val name: String, val description: String, val value: String) : Coefficients()
}
