package com.example.osagocalculation.presentation.insurances.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.osagocalculation.R
import com.example.osagocalculation.domain.entities.InsuranceDomain
import com.example.osagocalculation.presentation.insurances.adapter.viewholder.InsuranceViewHolder

class InsurancesAdapter : RecyclerView.Adapter<InsuranceViewHolder>() {

    private val items = mutableListOf<InsuranceDomain>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InsuranceViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_insurance, parent, false)
        return InsuranceViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: InsuranceViewHolder, position: Int) {
        InsuranceItemBinder.onBind(holder, items[position])
    }

    override fun getItemCount(): Int = items.count()

    fun setData(newData: List<InsuranceDomain>) {
        items.clear()
        items.addAll(newData)
        notifyDataSetChanged()
    }

}
