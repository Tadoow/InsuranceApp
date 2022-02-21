package com.example.osagocalculation

import android.app.Application
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.util.CoilUtils
import com.example.osagocalculation.data.api.FactorsApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory

class App : Application() {

    private lateinit var okHttpClient: OkHttpClient
    lateinit var factorsApi: FactorsApi
    lateinit var imageLoader: ImageLoader

    override fun onCreate() {
        super.onCreate()

        okHttpClient = createOkhttpClient()
        factorsApi = initRetrofit(okHttpClient)
        imageLoader = ImageLoader.Builder(applicationContext)
            .okHttpClient(okHttpClient)
            .componentRegistry { add(SvgDecoder(applicationContext)) }
            .build()
    }

    private fun createOkhttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .cache(CoilUtils.createDefaultCache(applicationContext))
            .build()
    }

    private fun initRetrofit(client: OkHttpClient): FactorsApi {
        val contentType = CONTENT_TYPE.toMediaType()

        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .client(client)
            .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory(contentType))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        return retrofit.create(FactorsApi::class.java)
    }

    companion object {
        private const val URL = "http://mock.sravni-team.ru/"
        private const val CONTENT_TYPE = "application/json"
    }

}
