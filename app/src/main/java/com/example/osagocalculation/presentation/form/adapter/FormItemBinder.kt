package com.example.osagocalculation.presentation.form.adapter

import com.example.osagocalculation.data.dto.FormData
import com.example.osagocalculation.presentation.form.adapter.viewholder.FormViewHolder
import com.example.osagocalculation.presentation.form.listener.OnClickItemListener

object FormItemBinder {

    fun onBind(holder: FormViewHolder, item: FormData, listener: OnClickItemListener) {
        holder.setName(item.name)
        holder.setValue(item.value)

        if (item.value.isEmpty()) {
            holder.setTextVisibility(false)
        } else {
            holder.setTextVisibility(true)
            holder.adjustPaddings()
        }

        holder.itemView.setOnClickListener {
            listener.onFormItemClicked(item)
        }
    }

}
