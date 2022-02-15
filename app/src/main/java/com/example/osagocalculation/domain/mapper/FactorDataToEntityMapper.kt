package com.example.osagocalculation.domain.mapper

import com.example.osagocalculation.data.dto.FactorData
import com.example.osagocalculation.domain.entities.Factors

class FactorDataToEntityMapper : (List<FactorData>) -> List<Factors.FactorDomain> {

    override fun invoke(factors: List<FactorData>): List<Factors.FactorDomain> {
        return factors.map {
            Factors.FactorDomain(
                title = it.title,
                headerValue = it.headerValue,
                value = it.value,
                name = it.name,
                detailText = it.detailText
            )
        }
    }

}
