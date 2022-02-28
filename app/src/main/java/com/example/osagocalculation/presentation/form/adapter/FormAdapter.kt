package com.example.osagocalculation.presentation.form.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.osagocalculation.R
import com.example.osagocalculation.data.dto.FormData
import com.example.osagocalculation.presentation.form.adapter.viewholder.FormViewHolder
import com.example.osagocalculation.presentation.form.listener.OnFormClickListener

class FormAdapter(private val listener: OnFormClickListener) :
    RecyclerView.Adapter<FormViewHolder>() {

    val items: MutableList<FormData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FormViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_application_form, parent, false)
        return FormViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FormViewHolder, position: Int) {
        FormItemBinder.onBind(holder, items[position], listener)
    }

    override fun getItemCount(): Int = items.count()

    fun setData(newData: List<FormData>) {
        items.clear()
        items.addAll(newData)
        notifyDataSetChanged()
    }

    fun updateItem(item: FormData) {
        notifyItemChanged(items.indexOf(item))
    }

}
