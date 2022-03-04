package com.example.osagocalculation.di.application

import android.app.Application
import coil.ImageLoader
import com.example.osagocalculation.di.main.MainFragmentComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ApplicationComponent {

    fun getImageLoader(): ImageLoader

    fun getMainComponent(): MainFragmentComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): ApplicationComponent
    }

}
