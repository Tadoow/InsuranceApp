package com.example.osagocalculation

import android.app.Application
import android.content.SharedPreferences
import com.example.osagocalculation.data.api.FactorsApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory

class App : Application() {

    lateinit var factorsApi: FactorsApi
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate() {
        super.onCreate()

        factorsApi = initRetrofit()
        sharedPreferences = getSharedPreferences(PREFS, MODE_PRIVATE)
    }

    private fun initRetrofit(): FactorsApi {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val contentType = "application/json".toMediaType()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://mock.sravni-team.ru/")
            .client(client)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        return retrofit.create(FactorsApi::class.java)
    }

    companion object {
        const val PREFS = "APP_DATA"
    }

}
