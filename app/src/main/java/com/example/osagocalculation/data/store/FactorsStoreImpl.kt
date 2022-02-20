package com.example.osagocalculation.data.store

import android.content.SharedPreferences
import com.example.osagocalculation.data.dto.FactorData
import com.example.osagocalculation.data.dto.FormData
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class FactorsStoreImpl(private val sharedPreferences: SharedPreferences) : FactorsStore {

    private val initialFactorValues = listOf(
        "2 754 - 4 432 ₽",
        "0,6 - 1,6",
        "0,64 - 1,99",
        "0,5 - 2,45",
        "0,90 - 1,93",
        "1 или 1,99"
    )

    override fun getFormItems(): List<FormData> = mutableListOf(
        FormData("Город регистрации собственника", 64, "Москва", ""),
        FormData("Мощность автомобиля", 2, "121-150 л.с.", ""),
        FormData("Сколько водителей", 2, "2 водителя", ""),
        FormData("Возраст младшего из водителей", 64, "30 лет", ""),
        FormData("Минимальный стаж водителей", 64, "2 года", ""),
        FormData("Сколько лет не было аварий", 2, "2 года", "")
    )

    override fun saveFactorsInitialData(factors: List<FactorData>): List<FactorData> {
        val initialFactors = setInitialValues(factors)
        sharedPreferences.edit()
            .putString(INITIAL_DATA, Json.encodeToString(initialFactors))
            .apply()
        return initialFactors
    }

    override fun getFactorsInitialData(): List<FactorData>? {
        return sharedPreferences.getString(INITIAL_DATA, null)
            ?.let { Json.decodeFromString(it) }
    }

    private fun setInitialValues(factors: List<FactorData>): List<FactorData> {
        return factors.mapIndexed { index, factor ->
            FactorData(
                title = factor.title,
                headerValue = factor.title,
                value = initialFactorValues[index],
                name = factor.name,
                detailText = factor.detailText
            )
        }
    }

    companion object {
        const val INITIAL_DATA = "INITIAL_DATA"
    }

}
