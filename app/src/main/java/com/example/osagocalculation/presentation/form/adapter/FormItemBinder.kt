package com.example.osagocalculation.presentation.form.adapter

import com.example.osagocalculation.presentation.form.adapter.viewholder.FormViewHolder
import com.example.osagocalculation.presentation.form.listener.OnItemClickListener

object FormItemBinder {

    fun onBind(holder: FormViewHolder, item: String, listener: OnItemClickListener) {
        holder.setHint(item)
        holder.itemView.setOnClickListener {
            listener.formClicked()
        }
    }

}
