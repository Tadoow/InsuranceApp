package com.example.osagocalculation.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.osagocalculation.di.main.ViewModelKey
import com.example.osagocalculation.presentation.ViewModelFactory
import com.example.osagocalculation.presentation.insurances.InsurancesViewModel
import com.example.osagocalculation.presentation.main.SharedViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelBindsModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SharedViewModel::class)
    abstract fun bindSharedViewModel(viewModel: SharedViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(InsurancesViewModel::class)
    abstract fun bindInsuranceViewModel(viewModel: InsurancesViewModel): ViewModel
}
