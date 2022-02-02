package com.example.osagocalculation.domain

import com.example.osagocalculation.domain.entities.Coefficients

interface Repository {
    fun getData(): List<Coefficients>
    fun getFormData(): List<String>
}
