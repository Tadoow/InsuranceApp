package com.example.osagocalculation.presentation.form.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.osagocalculation.R
import com.example.osagocalculation.presentation.form.adapter.viewholder.FormViewHolder
import com.example.osagocalculation.presentation.form.listener.OnItemClickListener

class FormAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<FormViewHolder>() {

    private val items = arrayListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FormViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_application_form, parent, false)
        return FormViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FormViewHolder, position: Int) {
        val item = items[position]
        FormItemBinder.onBind(holder, item, listener)
    }

    override fun getItemCount(): Int = items.count()

    fun setData(newData: List<String>) {
        items.addAll(newData)
        notifyDataSetChanged()
    }

}
