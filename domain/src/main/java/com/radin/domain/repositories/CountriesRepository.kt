package com.radin.domain.repositories

import com.radin.domain.utils.Either
import com.radin.domain.models.Country
import kotlinx.coroutines.flow.Flow

interface CountriesRepository {

    fun searchCountriesByName(name: String, fullText: Boolean): Flow<Either<String, List<Country>>>
}