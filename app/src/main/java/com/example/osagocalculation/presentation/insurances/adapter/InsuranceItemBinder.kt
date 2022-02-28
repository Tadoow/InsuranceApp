package com.example.osagocalculation.presentation.insurances.adapter

import com.example.osagocalculation.domain.entities.InsuranceDomain
import com.example.osagocalculation.presentation.insurances.adapter.viewholder.InsuranceViewHolder
import com.example.osagocalculation.presentation.insurances.listener.OnInsuranceClickListener

object InsuranceItemBinder {

    fun onBind(
        holder: InsuranceViewHolder,
        item: InsuranceDomain,
        listener: OnInsuranceClickListener
    ) {
        holder.setInsuranceName(item.name)
        holder.setInsurancePrice(item.price)
        holder.setInsuranceRating(item.rating)
        if (item.iconUrl.isNullOrEmpty()) {
            holder.setInsuranceIconBackground(item.iconBackground, item.iconTitle, item.fontColor)
        } else {
            holder.setInsuranceIconUrl(item.iconUrl)
        }

        holder.itemView.setOnClickListener {
            holder.itemView.isClickable = false
            listener.onInsuranceClick(item)
        }
    }

}
