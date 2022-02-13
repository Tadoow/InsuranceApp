package com.example.osagocalculation.presentation.main.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.osagocalculation.databinding.ItemFactorBinding

class FactorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemFactorBinding.bind(itemView)

    fun setFactorTitle(title: String) {
        binding.textFactorTitle.text = title
    }

    fun setFactorName(name: String) {
        binding.textFactorName.text = "($name)"
    }

    fun setFactorValue(value: String) {
        binding.textFactorValue.text = value
    }

    fun setFactorDetailText(detailText: String) {
        binding.textFactorDetailText.text = detailText
    }

}
