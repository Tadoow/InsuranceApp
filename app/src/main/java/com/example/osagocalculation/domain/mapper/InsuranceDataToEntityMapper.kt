package com.example.osagocalculation.domain.mapper

import com.example.osagocalculation.data.dto.InsuranceData
import com.example.osagocalculation.domain.entities.InsuranceDomain

class InsuranceDataToEntityMapper : (List<InsuranceData>) -> List<InsuranceDomain> {

    override fun invoke(insurances: List<InsuranceData>): List<InsuranceDomain> {
        return insurances.map {
            InsuranceDomain(
                name = it.name,
                rating = it.rating,
                price = it.price,
                iconUrl = it.branding.iconUrl,
                iconTitle = it.branding.iconTitle,
                fontColor = it.branding.fontColor,
                iconBackground = it.branding.backgroundColor
            )
        }
    }

}
