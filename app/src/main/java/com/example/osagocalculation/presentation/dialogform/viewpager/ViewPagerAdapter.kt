package com.example.osagocalculation.presentation.dialogform.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.osagocalculation.data.dto.FormData

class ViewPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    private val items: MutableList<FormData> = mutableListOf()

    override fun getItemCount(): Int = items.size

    override fun createFragment(position: Int): Fragment {
        return ViewPagerFragment.newInstance(items[position])
    }

    fun setItems(formData: List<FormData>) {
        items.addAll(formData)
        notifyDataSetChanged()
    }

}
