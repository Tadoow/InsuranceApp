package com.example.osagocalculation.data

import com.example.osagocalculation.data.api.FactorsApi
import com.example.osagocalculation.data.dto.FactorData
import com.example.osagocalculation.data.dto.FormData
import com.example.osagocalculation.data.dto.FormRequest
import com.example.osagocalculation.data.store.FactorsStore
import com.example.osagocalculation.domain.Repository
import io.reactivex.rxjava3.core.Single

class RepositoryImpl(
    private val factorsApi: FactorsApi,
    private val factorsStore: FactorsStore
) : Repository {

    override fun getInitialFactors(): Single<List<FactorData>> {
        return if (factorsStore.getFactorsInitialData().isNullOrEmpty()) {
            factorsApi.getFactorsInitialData()
                .map { factorsStore.saveFactorsInitialData(it.factors) }
        } else {
            Single.fromCallable { factorsStore.getFactorsInitialData() }
        }
    }

    override fun getCalculatedFactors(formValues: List<FormData>): Single<List<FactorData>> {
        return factorsApi.sendFormValues(FormRequest(formValues))
            .map { it.factors }
    }

    override fun getFormItems(): List<FormData> {
        return factorsStore.getFormItems()
    }

}
