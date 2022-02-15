package com.example.osagocalculation.presentation.main.adapter

import com.example.osagocalculation.domain.entities.Factors
import com.example.osagocalculation.presentation.main.adapter.viewholder.FactorsHeaderViewHolder
import com.example.osagocalculation.presentation.main.listener.OnItemClickListener

object FactorsHeaderBinder {

    fun onBind(holder: FactorsHeaderViewHolder, item: Factors, listener: OnItemClickListener) {
        holder.setFactorsNames(buildFactorsNames(item))
        holder.itemView.setOnClickListener {
            listener.onHeaderClicked()
            holder.toggleHeaderState()
        }
    }

    private fun buildFactorsNames(item: Factors): String {
        val header = item as Factors.Header
        val names = StringBuilder()
        for (value in header.headerValues) {
            if (value == header.headerValues.last()) {
                names.append("<font color='#00AFFF'>$value</font>")
            } else {
                names.append("<font color='#00AFFF'>$value</font><font color='#99A1AB'>x</font>")
            }
        }
        return names.toString()
    }

}
