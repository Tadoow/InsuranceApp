package com.example.osagocalculation.presentation.form.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.osagocalculation.databinding.ItemApplicationFormBinding

class FormViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemApplicationFormBinding.bind(itemView)

    fun setHint(hint: String) {
        binding.textApplicationFormHint.hint = hint
    }

}
