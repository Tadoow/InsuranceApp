package com.example.osagocalculation.data.store

import android.content.Context
import com.example.osagocalculation.data.dto.FactorData
import com.example.osagocalculation.data.dto.FormData
import com.example.osagocalculation.utils.AssetsHelper
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream

//Отказался, в итоге, совсем от затеи с шаред префами, положил все в ассеты как и обсуждали на созвоне
class FactorsStoreImpl(private val context: Context) : FactorsStore {

    override fun getFormData(): List<FormData>? {
        return Json.decodeFromStream(AssetsHelper.readFile(context, FORM_DATA))
    }

    override fun getFactorsInitialData(): List<FactorData>? {
        return Json.decodeFromStream(AssetsHelper.readFile(context, FACTORS_INITIAL_DATA))
    }

    companion object {
        private const val FACTORS_INITIAL_DATA = "factorsInitialData.json"
        private const val FORM_DATA = "formData.json"
    }

}
