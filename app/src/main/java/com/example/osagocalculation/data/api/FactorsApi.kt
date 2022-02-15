package com.example.osagocalculation.data.api

import com.example.osagocalculation.data.dto.FactorsResponse
import com.example.osagocalculation.data.dto.FormRequest
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface FactorsApi {

    @GET("mobile/internship/v1/osago/rationDetail")
    fun getFactorsInitialData(): Single<FactorsResponse>

    @POST("mobile/internship/v1/osago/rationDetail")
    fun sendFormValues(
        @Body formValues: FormRequest
    ): Single<FactorsResponse>

}
