package com.example.osagocalculation.presentation.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.osagocalculation.R
import com.example.osagocalculation.domain.entities.Factors
import com.example.osagocalculation.presentation.main.adapter.viewholder.FactorViewHolder
import com.example.osagocalculation.presentation.main.adapter.viewholder.FactorsHeaderViewHolder
import com.example.osagocalculation.presentation.main.listener.OnItemClickListener

class MainAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = arrayListOf<Factors>()
    private val itemsToToggle = arrayListOf<Factors>()

    override fun getItemViewType(position: Int): Int =
        when (items[position]) {
            is Factors.Header -> R.layout.item_factors_header
            is Factors.FactorDomain -> R.layout.item_factor
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            R.layout.item_factors_header -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(viewType, parent, false)
                FactorsHeaderViewHolder(itemView)
            }
            R.layout.item_factor -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(viewType, parent, false)
                FactorViewHolder(itemView)
            }
            else -> throw IllegalArgumentException("Such viewType not found: $viewType")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        when (holder) {
            is FactorsHeaderViewHolder -> FactorsHeaderBinder.onBind(holder, item, listener)
            is FactorViewHolder -> FactorBinder.onBind(holder, item)
            else -> throw IllegalArgumentException("Such holder not found $holder")
        }
    }

    override fun getItemCount(): Int = items.count()

    fun setData(newData: List<Factors>) {
        items.clear()
        itemsToToggle.clear()
        val header = newData.first() as Factors.Header
        if (header.expanded) {
            items.addAll(newData)
        } else {
            items.add(newData.first())
        }
        itemsToToggle.addAll(newData)
        itemsToToggle.removeFirst()
        notifyDataSetChanged()
    }

    fun toggleSection() {
        val header = items.first() as Factors.Header
        if (header.expanded) {
            items.removeAll(itemsToToggle)
            notifyItemRangeRemoved(1, itemsToToggle.count())
        } else {
            items.addAll(itemsToToggle)
            notifyItemRangeInserted(1, itemsToToggle.count())
        }
        header.expanded = !header.expanded
    }

}
