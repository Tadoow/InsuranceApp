package com.example.osagocalculation.presentation.main.adapter

import com.example.osagocalculation.domain.entities.Factors
import com.example.osagocalculation.presentation.main.adapter.viewholder.FactorViewHolder

object FactorBinder {

    fun onBind(holder: FactorViewHolder, item: Factors) {
        val factor = item as Factors.FactorDomain
        holder.setFactorTitle(factor.title)
        holder.setFactorName(factor.name)
        holder.setFactorValue(factor.value)
        holder.setFactorDetailText(factor.detailText)
    }

}
