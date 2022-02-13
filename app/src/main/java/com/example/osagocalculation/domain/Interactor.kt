package com.example.osagocalculation.domain

import com.example.osagocalculation.data.dto.FormData
import com.example.osagocalculation.domain.entities.Factors
import com.example.osagocalculation.domain.mapper.FactorDataToEntityMapper
import io.reactivex.rxjava3.core.Single

class Interactor(private val repository: Repository) {

    private val factorDataToEntityMapper = FactorDataToEntityMapper()

    fun getInitialFactors(): Single<List<Factors>> {
        return repository.getInitialFactors()
            .map(factorDataToEntityMapper)
            .flatMap { factors ->
                Single.fromCallable {
                    val items = arrayListOf<Factors>(createHeader(factors))
                    items.addAll(factors)
                    return@fromCallable items
                }
            }
    }

    fun getCalculatedFactors(formValues: List<FormData>): Single<List<Factors>> {
        return repository.getCalculatedFactors(formValues)
            .map(factorDataToEntityMapper)
            .flatMap { factors ->
                Single.fromCallable {
                    val items = arrayListOf<Factors>(createHeader(factors))
                    items.addAll(factors)
                    return@fromCallable items
                }
            }
    }

    fun getFormItems(): List<FormData> {
        return repository.getFormItems()
    }

    private fun createHeader(factors: List<Factors.FactorDomain>): Factors.Header {
        val headerValues = arrayListOf<String>()
        for (factor in factors) {
            headerValues.add(factor.headerValue)
        }
        return Factors.Header(headerValues)
    }

}
