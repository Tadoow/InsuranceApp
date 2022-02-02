package com.example.osagocalculation.presentation.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.osagocalculation.R
import com.example.osagocalculation.domain.entities.Coefficients
import com.example.osagocalculation.presentation.main.adapter.viewholder.CoefficientHeaderViewHolder
import com.example.osagocalculation.presentation.main.adapter.viewholder.CoefficientViewHolder
import com.example.osagocalculation.presentation.main.listener.OnItemClickListener

class MainAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = arrayListOf<Coefficients>()
    private val itemsToToggle = arrayListOf<Coefficients>()

    override fun getItemViewType(position: Int): Int =
        when (items[position]) {
            is Coefficients.Header -> R.layout.item_coefficients_header
            is Coefficients.Coefficient -> R.layout.item_coefficient
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            R.layout.item_coefficients_header -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(viewType, parent, false)
                CoefficientHeaderViewHolder(itemView)
            }
            R.layout.item_coefficient -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(viewType, parent, false)
                CoefficientViewHolder(itemView)
            }
            else -> throw IllegalArgumentException("Such viewType not found: $viewType")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        when (holder) {
            is CoefficientHeaderViewHolder -> CoefficientHeaderBinder.onBind(holder, item, listener)
            is CoefficientViewHolder -> CoefficientBinder.onBind(holder, item)
            else -> throw IllegalArgumentException("Such holder not found $holder")
        }
    }

    override fun getItemCount(): Int = items.count()

    fun setData(newData: List<Coefficients>) {
        items.add(newData.first())
        itemsToToggle.addAll(newData)
        itemsToToggle.removeFirst()
        notifyDataSetChanged()
    }

    fun toggleSection() {
        val header = items.first() as Coefficients.Header
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
