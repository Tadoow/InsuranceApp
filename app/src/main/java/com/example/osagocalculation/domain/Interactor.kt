package com.example.osagocalculation.domain

import com.example.osagocalculation.data.dto.FormData
import com.example.osagocalculation.domain.entities.Factors
import com.example.osagocalculation.domain.entities.InsuranceDomain
import com.example.osagocalculation.domain.mapper.EntityToFactorDataMapper
import com.example.osagocalculation.domain.mapper.FactorDataToEntityMapper
import com.example.osagocalculation.domain.mapper.InsuranceDataToEntityMapper
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class Interactor @Inject constructor(private val repository: Repository) {

    private val factorDataToEntityMapper = FactorDataToEntityMapper()
    private val insuranceDataToEntityMapper = InsuranceDataToEntityMapper()
    private val entityToFactorDataMapper = EntityToFactorDataMapper()

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

    fun getInsurances(factors: List<Factors>): Single<List<InsuranceDomain>> {
        val factorsData = entityToFactorDataMapper(factors)
        return repository.getInsurances(factorsData)
            .map(insuranceDataToEntityMapper)
    }

    fun getFormItems(): Single<List<FormData>> {
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
