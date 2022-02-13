package com.example.osagocalculation.domain

import com.example.osagocalculation.data.dto.FactorData
import com.example.osagocalculation.data.dto.FormData
import io.reactivex.rxjava3.core.Single

interface Repository {
    fun getInitialFactors(): Single<List<FactorData>>
    fun getCalculatedFactors(formValues: List<FormData>): Single<List<FactorData>>
    fun getFormItems(): List<FormData>
}
