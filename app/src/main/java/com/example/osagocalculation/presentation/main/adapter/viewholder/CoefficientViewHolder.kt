package com.example.osagocalculation.presentation.main.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.osagocalculation.databinding.ItemCoefficientBinding

class CoefficientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemCoefficientBinding.bind(itemView)

    fun setCoefficientName(name: String) {
        binding.textCoefficientName.text = name
    }

    fun setCoefficientValue(value: String) {
        binding.textCoefficientValue.text = value
    }

    fun setCoefficientDescription(description: String) {
        binding.textCoefficientDescription.text = description
    }

}
