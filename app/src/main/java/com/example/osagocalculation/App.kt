package com.example.osagocalculation

import android.app.Application
import androidx.fragment.app.Fragment
import coil.ImageLoader
import com.example.osagocalculation.di.application.ApplicationComponent
import com.example.osagocalculation.di.application.DaggerApplicationComponent
import com.example.osagocalculation.di.main.MainFragmentComponent

class App : Application() {

    private lateinit var appComponent: ApplicationComponent
    lateinit var imageLoader: ImageLoader

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerApplicationComponent.factory().create(this)
        imageLoader = appComponent.getImageLoader()
    }

}

fun Fragment.getFragmentComponent(): MainFragmentComponent =
    DaggerApplicationComponent.factory().create(context?.applicationContext as Application)
        .getMainComponent().create()