package com.example.osagocalculation.presentation.main.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.osagocalculation.databinding.ItemCoefficientsHeaderBinding

class CoefficientHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemCoefficientsHeaderBinding.bind(itemView)

    fun setCoefficientsNames(names: String) {
        binding.textCoefficientsNames.text = names
    }

    fun toggleHeaderState() {
        binding.imageArrow.isSelected = !binding.imageArrow.isSelected
    }

}
