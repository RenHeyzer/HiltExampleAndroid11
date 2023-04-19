package com.example.hiltexampleandroid11.di

import com.radin.data.remote.RetrofitClient
import com.radin.data.remote.api.CountriesApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    val retrofitClient = RetrofitClient()

    @Provides
    @Singleton
    fun provideCountriesApiService(): CountriesApiService {
        return retrofitClient.countriesApiService
    }

//    @Provides
//    @Singleton
//    fun provideCountriesRepository(apiService: CountriesApiService): CountriesRepository {
//        return CountriesRepositoryImpl(apiService)
//    }
}