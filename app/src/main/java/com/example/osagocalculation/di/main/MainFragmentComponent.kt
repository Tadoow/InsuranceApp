package com.example.osagocalculation.di.main

import androidx.lifecycle.ViewModelProvider
import com.example.osagocalculation.di.ViewModelBindsModule
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [ViewModelBindsModule::class])
interface MainFragmentComponent {

    fun getViewModelFactory(): ViewModelProvider.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainFragmentComponent
    }

}
