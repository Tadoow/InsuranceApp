package com.example.osagocalculation.data.api

import com.example.osagocalculation.data.dto.FactorsRequest
import com.example.osagocalculation.data.dto.FactorsResponse
import com.example.osagocalculation.data.dto.FormRequest
import com.example.osagocalculation.data.dto.InsurancesResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface FactorsApi {

    @POST("mobile/internship/v1/osago/rationDetail")
    fun sendForm(
        @Body form: FormRequest
    ): Single<FactorsResponse>

    @POST("mobile/internship/v1/osago/startCalculation")
    fun sendFactors(
        @Body factors: FactorsRequest
    ): Single<InsurancesResponse>

}
