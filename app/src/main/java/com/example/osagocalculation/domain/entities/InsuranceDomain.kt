package com.example.osagocalculation.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InsuranceDomain(
    val name: String,
    val rating: String,
    val price: String,
    val iconUrl: String?,
    val iconTitle: String,
    val fontColor: Int,
    val iconBackground: Int
) : Parcelable
