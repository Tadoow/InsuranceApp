package com.example.osagocalculation.data.store

import com.example.osagocalculation.data.dto.FactorData
import com.example.osagocalculation.data.dto.FormData

interface FactorsStore {
    fun saveFactorsInitialData(factors: List<FactorData>): List<FactorData>
    fun getFactorsInitialData(): List<FactorData>?
    fun getFormItems(): List<FormData>
}
