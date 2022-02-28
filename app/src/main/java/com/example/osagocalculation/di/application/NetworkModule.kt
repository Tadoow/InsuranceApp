package com.example.osagocalculation.di.application

import android.content.Context
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.util.CoilUtils
import com.example.osagocalculation.data.api.FactorsApi
import com.example.osagocalculation.di.main.MainFragmentComponent
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import javax.inject.Singleton

@Module(includes = [BindsModule::class], subcomponents = [MainFragmentComponent::class])
class NetworkModule {

    @Singleton
    @Provides
    fun provideOkhttpClient(applicationContext: Context): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .cache(CoilUtils.createDefaultCache(applicationContext))
            .build()
    }

    @Singleton
    @Provides
    fun provideApi(client: OkHttpClient): FactorsApi {
        val contentType = CONTENT_TYPE.toMediaType()

        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .client(client)
            .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory(contentType))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        return retrofit.create(FactorsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideImageLoader(client: OkHttpClient, applicationContext: Context): ImageLoader {
        return ImageLoader.Builder(applicationContext)
            .okHttpClient(client)
            .componentRegistry { add(SvgDecoder(applicationContext)) }
            .build()
    }

    companion object {
        private const val URL = "http://mock.sravni-team.ru/"
        private const val CONTENT_TYPE = "application/json"
    }

}
