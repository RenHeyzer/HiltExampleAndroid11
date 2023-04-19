package com.example.hiltexampleandroid11.di

import com.radin.data.repositories.CountriesRepositoryImpl
import com.radin.domain.repositories.CountriesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesModule {

    @Binds
    fun bindCountriesRepository(repositoryImpl: CountriesRepositoryImpl): CountriesRepository
}