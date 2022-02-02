package com.example.osagocalculation.presentation.main.adapter

import com.example.osagocalculation.domain.entities.Coefficients
import com.example.osagocalculation.presentation.main.adapter.viewholder.CoefficientViewHolder

object CoefficientBinder {

    fun onBind(holder: CoefficientViewHolder, item: Coefficients) {
        val coefficient = item as Coefficients.Coefficient
        holder.setCoefficientName(coefficient.name)
        holder.setCoefficientDescription(coefficient.description)
        holder.setCoefficientValue(coefficient.value)
    }

}
