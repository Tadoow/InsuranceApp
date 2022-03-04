package com.example.osagocalculation.presentation.form.adapter

import com.example.osagocalculation.data.dto.FormData
import com.example.osagocalculation.presentation.form.adapter.viewholder.FormViewHolder
import com.example.osagocalculation.presentation.form.listener.OnFormClickListener

object FormItemBinder {

    fun onBind(holder: FormViewHolder, item: FormData, listener: OnFormClickListener) {
        holder.setName(item.name)
        holder.setValue(item.value)

        if (item.value.isEmpty()) {
            holder.setTextVisibility(false)
            holder.setInitialPaddings()
            holder.setInitialFontSize()
        } else {
            holder.setTextVisibility(true)
            holder.adjustPaddings()
            holder.adjustFontSize()
        }

        holder.itemView.setOnClickListener {
            holder.itemView.isClickable = false
            listener.onFormClick(item)
        }
    }

}
