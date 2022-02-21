package com.example.osagocalculation.data

import com.example.osagocalculation.data.api.FactorsApi
import com.example.osagocalculation.data.dto.*
import com.example.osagocalculation.data.store.FactorsStore
import com.example.osagocalculation.domain.Repository
import io.reactivex.rxjava3.core.Single

class RepositoryImpl(
    private val factorsApi: FactorsApi,
    private val factorsStore: FactorsStore
) : Repository {

    override fun getInitialFactors(): Single<List<FactorData>> {
        return Single.fromCallable { factorsStore.getFactorsInitialData() }
    }

    override fun getCalculatedFactors(formValues: List<FormData>): Single<List<FactorData>> {
        return factorsApi.sendForm(FormRequest(formValues))
            .map { it.factors }
    }

    override fun getFormItems(): Single<List<FormData>> {
        return Single.fromCallable { factorsStore.getFormData() }
    }

    override fun getInsurances(factors: List<FactorData>): Single<List<InsuranceData>> {
        return factorsApi.sendFactors(FactorsRequest(factors))
            .map { it.offers }
    }

}
