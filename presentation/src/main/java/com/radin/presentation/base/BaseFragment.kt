package com.radin.presentation.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.radin.presentation.state.UIState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

open class BaseFragment : Fragment() {

    protected open fun <T> StateFlow<UIState<T>>.collectState(
        loading: (() -> Unit)? = null,
        error: ((message: String) -> Unit)? = null,
        success: (T) -> Unit,
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                this@collectState.collect {
                    when (it) {
                        is UIState.Error -> {
                            error?.let { error ->
                                error(it.error)
                            }
                        }
                        is UIState.Loading -> {
                            loading?.let {
                                it()
                            }
                        }
                        is UIState.Success -> {
                            success(it.data)
                        }
                    }
                }
            }
        }
    }
}