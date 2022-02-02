package com.example.osagocalculation.presentation.main.adapter

import com.example.osagocalculation.domain.entities.Coefficients
import com.example.osagocalculation.presentation.main.adapter.viewholder.CoefficientHeaderViewHolder
import com.example.osagocalculation.presentation.main.listener.OnItemClickListener

object CoefficientHeaderBinder {

    fun onBind(
        holder: CoefficientHeaderViewHolder,
        item: Coefficients,
        listener: OnItemClickListener
    ) {
        holder.setCoefficientsNames(buildCoefficientsNames(item))
        holder.itemView.setOnClickListener {
            listener.headerClicked()
            holder.toggleHeaderState()
        }
    }

    private fun buildCoefficientsNames(item: Coefficients): String {
        val header = item as Coefficients.Header
        val names = StringBuilder()
        for (coefficient in header.coefficients) {
            if (coefficient == header.coefficients.last()) {
                names.append(coefficient.name.substringBefore(" "))
            } else {
                names.append("${coefficient.name.substringBefore(" ")}x")
            }
        }
        return names.toString()
    }

}
