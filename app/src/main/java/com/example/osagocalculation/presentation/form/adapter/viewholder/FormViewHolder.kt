package com.example.osagocalculation.presentation.form.adapter.viewholder

import android.view.View
import androidx.core.view.isVisible
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import com.example.osagocalculation.databinding.ItemApplicationFormBinding

class FormViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemApplicationFormBinding.bind(itemView)

    fun setName(name: String) {
        binding.textApplicationFormHint.text = name
    }

    fun setValue(value: String) {
        binding.textApplicationForm.text = value
    }

    fun setTextVisibility(visible: Boolean) {
        binding.textApplicationForm.isVisible = visible
    }

    fun adjustPaddings() {
        binding.textApplicationFormHint.setPadding(0)
    }

    fun setInitialPaddings() {
        binding.textApplicationFormHint.setPadding(0, 18, 0, 18)
    }

}
