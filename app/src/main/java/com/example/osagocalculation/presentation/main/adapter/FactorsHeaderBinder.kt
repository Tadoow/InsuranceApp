package com.example.osagocalculation.presentation.main.adapter

import com.example.osagocalculation.domain.entities.Factors
import com.example.osagocalculation.presentation.main.adapter.viewholder.FactorsHeaderViewHolder
import com.example.osagocalculation.presentation.main.listener.OnFactorClickListener

object FactorsHeaderBinder {

    fun onBind(holder: FactorsHeaderViewHolder, item: Factors, listener: OnFactorClickListener) {
        val header = item as Factors.Header
        holder.setFactorsNames(buildFactorsNames(header))
        holder.setHeaderState(header.expanded)
        holder.itemView.setOnClickListener {
            listener.onHeaderClicked()
            holder.toggleHeaderState()
        }
    }

    private fun buildFactorsNames(item: Factors.Header): String {
        val names = StringBuilder()
        for (value in item.headerValues) {
            if (value == item.headerValues.last()) {
                names.append("<font color='#00AFFF'>$value</font>")
            } else {
                names.append("<font color='#00AFFF'>$value</font><font color='#99A1AB'>x</font>")
            }
        }
        return names.toString()
    }

}
