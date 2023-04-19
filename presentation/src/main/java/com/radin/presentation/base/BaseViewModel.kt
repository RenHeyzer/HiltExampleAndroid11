package com.radin.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radin.domain.utils.Either
import com.radin.presentation.state.UIState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    protected fun <UI, T1 : Any, T2 : Any, Domain> MutableStateFlow<UIState<UI>>.collectFiltered(
        filterState1: StateFlow<T1>,
        filterState2: StateFlow<T2>,
        request: (filter1: T1, filter2: T2) -> Flow<Either<String, Domain>>,
        mapToUI: (Domain) -> UI
    ) {
        viewModelScope.launch {
            combine(filterState1, filterState2) { first, second ->
                first to second
            }.flatMapLatest {
                request(it.first, it.second)
            }.collect {
                when (it) {
                    is Either.Left -> {
                        it.message?.let { message ->
                            this@collectFiltered.value = UIState.Error(message)
                        }
                    }
                    is Either.Right -> {
                        it.data?.let { data ->
                            this@collectFiltered.value = UIState.Success(mapToUI(data))
                        }
                    }
                }
            }
        }
    }

    protected open fun <D, U> Flow<Either<String, D>>.collectToState(
        state: MutableStateFlow<UIState<U>>,
        mapToUI: (D) -> U
    ) {
        viewModelScope.launch {
            this@collectToState.collect {
                when (it) {
                    is Either.Left -> it.message?.let { message ->
                        state.value = UIState.Error(message)
                    }
                    is Either.Right -> it.data?.let { data ->
                        state.value = UIState.Success(mapToUI(data))
                    }
                }
            }
        }
    }

    protected open fun <D, U> MutableStateFlow<UIState<U>>.collectRequest(
        request: () -> Flow<Either<String, D>>,
        mapToUI: (D) -> U
    ) {
        viewModelScope.launch {
            request().collect {
                when (it) {
                    is Either.Left -> it.message?.let { message ->
                        this@collectRequest.value = UIState.Error(message)
                    }
                    is Either.Right -> it.data?.let { data ->
                        this@collectRequest.value = UIState.Success(mapToUI(data))
                    }
                }
            }
        }
    }
}