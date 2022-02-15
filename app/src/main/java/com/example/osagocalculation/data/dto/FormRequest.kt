package com.example.osagocalculation.data.dto

import kotlinx.serialization.Serializable

@Serializable
class FormRequest(
    val formValues: List<FormData>
)
