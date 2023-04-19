package com.radin.presentation.ui.fragments

import androidx.lifecycle.SavedStateHandle
import com.radin.domain.usecases.SearchCountriesByNameUseCase
import com.radin.presentation.base.BaseViewModel
import com.radin.presentation.models.CountryUI
import com.radin.presentation.models.toUI
import com.radin.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val searchCountriesByNameUseCase: SearchCountriesByNameUseCase
) : BaseViewModel() {

    private val _countriesState =
        MutableStateFlow<UIState<List<CountryUI>>>(UIState.Loading())
    val countriesState = _countriesState.asStateFlow()

    private val searchResultData: StateFlow<String> =
        savedStateHandle.getStateFlow("name", "")

    private val fullTextData: StateFlow<Boolean> =
        savedStateHandle.getStateFlow("fullText", false)

    private val thirdResultData: StateFlow<Int> =
        savedStateHandle.getStateFlow("third", -1)

    fun setCountryName(name: String) {
        savedStateHandle["name"] = name
    }

    fun setFullText(boolean: Boolean) {
        savedStateHandle["fullText"] = boolean
    }

    init {
        searchCountriesByName()
    }

    private fun searchCountriesByName() {
        _countriesState.collectToState(
            request = {
                searchCountriesByNameUseCase("", false)
            },
            mapToUI = {
                it.map { country ->
                    country.toUI()
                }
            }
        )
        /*_countriesState.collectFiltered(
            searchResultData, fullTextData,
            request = { filter1, filter2 ->
                searchCountriesByNameUseCase(filter1, filter2)
            },
            mapToUI = {
                it.map { country ->
                    country.toUI()
                }
            }
        )*/
        /*viewModelScope.launch {
            combine(searchResultData, fullTextData) { first, second ->
                Filter(first, second)
            }.flatMapLatest {
                searchCountriesByNameUseCase(it.name, it.fullText)
            }.collect { either ->
                when (either) {
                    is Either.Left -> either.message?.let {
                        _countriesState.value = UIState.Error(it)
                    }
                    is Either.Right -> either.data?.let {
                        _countriesState.value = UIState.Success(it.map { country ->
                            country.toUI()
                        })
                    }
                }
            }
        }*/
    }
}

data class Filter(
    val name: String,
    val fullText: Boolean,
)