package com.example.osagocalculation.presentation.insurances.adapter

import com.example.osagocalculation.domain.entities.InsuranceDomain
import com.example.osagocalculation.presentation.insurances.adapter.viewholder.InsuranceViewHolder

object InsuranceItemBinder {

    fun onBind(holder: InsuranceViewHolder, item: InsuranceDomain) {
        holder.setInsuranceName(item.name)
        holder.setInsurancePrice(item.price.toString())
        holder.setInsuranceRating(item.rating.toString())
        if (item.iconUrl.isNullOrEmpty()) {
            holder.setInsuranceIconBackground(item.iconBackground, item.iconTitle, item.fontColor)
        } else {
            holder.setInsuranceIconUrl(item.iconUrl)
        }
    }

}
