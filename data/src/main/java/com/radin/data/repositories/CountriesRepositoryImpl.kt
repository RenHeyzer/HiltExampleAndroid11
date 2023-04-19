package com.radin.data.repositories

import com.radin.data.base.BaseRepository
import com.radin.data.remote.api.CountriesApiService
import com.radin.domain.repositories.CountriesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountriesRepositoryImpl @Inject constructor(
    private val apiService: CountriesApiService
) : BaseRepository(), CountriesRepository {

    override fun searchCountriesByName(name: String, fullText: Boolean) = doRequest {
        apiService.searchCountriesByName(name, fullText).map {
            it.toDomain()
        }
    }
}