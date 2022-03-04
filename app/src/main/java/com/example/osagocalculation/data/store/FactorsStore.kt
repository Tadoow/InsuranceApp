package com.example.osagocalculation.data.store

import com.example.osagocalculation.data.dto.FactorData
import com.example.osagocalculation.data.dto.FormData

interface FactorsStore {
    fun getFactorsInitialData(): List<FactorData>?
    fun getFormData(): List<FormData>?
}
