package com.example.osagocalculation.domain.mapper

import com.example.osagocalculation.data.dto.FactorData
import com.example.osagocalculation.domain.entities.Factors

class EntityToFactorDataMapper : (List<Factors>) -> List<FactorData> {

    override fun invoke(factors: List<Factors>): List<FactorData> {
        return factors.filterIsInstance<Factors.FactorDomain>().map {
            FactorData(
                title = it.title,
                headerValue = it.headerValue,
                value = it.value,
                name = it.name,
                detailText = it.detailText
            )
        }
    }

}
