package com.example.osagocalculation.presentation.insurances.adapter.viewholder

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.request.ImageRequest
import com.example.osagocalculation.App
import com.example.osagocalculation.R
import com.example.osagocalculation.databinding.ItemInsuranceBinding

class InsuranceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemInsuranceBinding.bind(itemView)

    fun setInsuranceName(name: String) {
        binding.textInsuranceName.text = name
    }

    fun setInsuranceRating(rating: String) {
        binding.textInsuranceRating.text = rating
    }

    fun setInsurancePrice(price: String) {
        binding.textInsurancePrice.text = itemView.resources.getString(R.string.ruble_sign, price)
    }

    fun setInsuranceIconUrl(url: String) {
        binding.textInsuranceIconTitle.isVisible = false
        val request = ImageRequest.Builder(itemView.context.applicationContext)
            .data(url)
            .crossfade(true)
            .target(binding.imageInsurance)
            .build()
        (itemView.context.applicationContext as App).imageLoader.enqueue(request)
    }

    fun setInsuranceIconBackground(backgroundColor: String, title: String, fontColor: String) {
        binding.imageInsurance.setImageDrawable(ColorDrawable(Color.parseColor("#$backgroundColor")))
        binding.textInsuranceIconTitle.isVisible = true
        binding.textInsuranceIconTitle.setTextColor(Color.parseColor("#$fontColor"))
        binding.textInsuranceIconTitle.text = title
    }

}
