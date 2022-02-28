package com.example.osagocalculation.presentation.insurances.listener

import com.example.osagocalculation.domain.entities.InsuranceDomain

interface OnInsuranceClickListener {
    fun onInsuranceClick(insurance: InsuranceDomain)
}
