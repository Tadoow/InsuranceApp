package com.example.osagocalculation.presentation.form.adapter

import android.util.Log
import com.example.osagocalculation.presentation.form.adapter.viewholder.FormViewHolder
import com.example.osagocalculation.presentation.form.listener.OnItemClickListener

object FormItemBinder {

    fun onBind(holder: FormViewHolder, item: String, listener: OnItemClickListener) {
        holder.setHint(item)
        Log.d("TAG", "onBind: set hint")

        holder.setClickListener {
            Log.d("TAG", "onBind: ")
            listener.onFormClicked(item)
        }
    }

}
