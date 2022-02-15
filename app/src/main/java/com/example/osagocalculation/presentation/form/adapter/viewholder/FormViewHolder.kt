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
        binding.textApplicationFormHint.setPadding(PADDING)
    }

    fun setInitialPaddings() {
        binding.textApplicationFormHint.setPadding(
            PADDING_LEFT,
            PADDING_TOP,
            PADDING_RIGHT,
            PADDING_BOTTOM
        )
    }

    companion object {
        private const val PADDING = 0
        private const val PADDING_LEFT = 0
        private const val PADDING_RIGHT = 0
        private const val PADDING_TOP = 18
        private const val PADDING_BOTTOM = 18
    }

}
