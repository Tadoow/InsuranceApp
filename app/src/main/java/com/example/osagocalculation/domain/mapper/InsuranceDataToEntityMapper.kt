package com.example.osagocalculation.domain.mapper

import android.graphics.Color
import com.example.osagocalculation.data.dto.InsuranceData
import com.example.osagocalculation.domain.entities.InsuranceDomain

class InsuranceDataToEntityMapper : (List<InsuranceData>) -> List<InsuranceDomain> {

    override fun invoke(insurances: List<InsuranceData>): List<InsuranceDomain> {
        return insurances.map {
            InsuranceDomain(
                name = it.name,
                rating = it.rating.toString(),
                price = it.price.toString(),
                iconUrl = it.branding.iconUrl,
                iconTitle = it.branding.iconTitle,
                fontColor = getColor(it.branding.fontColor),
                iconBackground = getColor(it.branding.backgroundColor)
            )
        }
    }

    private fun getColor(code: String): Int {
        return try {
            Color.parseColor("#$code")
        } catch (e: RuntimeException) {
            DEFAULT_COLOR
        }
    }

    companion object {
        private const val DEFAULT_COLOR = 0
    }

}
