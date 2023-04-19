package com.radin.domain.usecases

import com.radin.domain.utils.Either
import com.radin.domain.models.Country
import com.radin.domain.repositories.CountriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchCountriesByNameUseCase @Inject constructor(
    private val repository: CountriesRepository
) {

    operator fun invoke(name: String, fullText: Boolean): Flow<Either<String, List<Country>>> {
        return repository.searchCountriesByName(name, fullText)
    }
}