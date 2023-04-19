package com.radin.data.remote.api

import com.radin.data.remote.dtos.CountryDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CountriesApiService {

    @GET("name/{name}")
    suspend fun searchCountriesByName(
        @Path("name") name: String,
        @Query("fullText") fullText: Boolean
    ): List<CountryDto>
}