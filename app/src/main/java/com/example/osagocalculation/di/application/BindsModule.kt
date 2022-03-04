package com.example.osagocalculation.di.application

import android.app.Application
import android.content.Context
import com.example.osagocalculation.data.RepositoryImpl
import com.example.osagocalculation.data.store.FactorsStore
import com.example.osagocalculation.data.store.FactorsStoreImpl
import com.example.osagocalculation.domain.Repository
import dagger.Binds
import dagger.Module

@Module
abstract class BindsModule {

    @Binds
    abstract fun bindApplication(application: Application): Context

    @Binds
    abstract fun bindRepository(repository: RepositoryImpl): Repository

    @Binds
    abstract fun bindStore(store: FactorsStoreImpl): FactorsStore
}
