package com.example.osagocalculation.presentation.main.adapter.viewholder

import android.text.Html
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.osagocalculation.databinding.ItemFactorsHeaderBinding

class FactorsHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemFactorsHeaderBinding.bind(itemView)

    fun setFactorsNames(names: String) {
        binding.textFactorsHeaderValue.text = Html.fromHtml(names)
    }

    fun toggleHeaderState() {
        binding.imageArrow.isSelected = !binding.imageArrow.isSelected
    }

}
