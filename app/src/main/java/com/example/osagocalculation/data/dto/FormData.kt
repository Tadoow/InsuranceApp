package com.example.osagocalculation.data.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class FormData(
    val name: String,
    val inputType: Int,
    val hint: String,
    var value: String
) : Parcelable
