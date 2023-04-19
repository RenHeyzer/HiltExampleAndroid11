package com.radin.presentation.extensions

import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener

fun SearchView.setOnQueryTextListener(
    onTextChange: ((text: String) -> Unit)? = null,
    onTextSubmit: ((text: String) -> Unit)? = null
) {
    this.setOnQueryTextListener(object : OnQueryTextListener {
        override fun onQueryTextSubmit(_text: String?): Boolean {
            _text?.let { text ->
                onTextSubmit?.let { it(text) }
            }
            return false
        }

        override fun onQueryTextChange(_text: String?): Boolean {
            _text?.let { text ->
                onTextChange?.let { it -> it(text) }
            }
            return false
        }
    })
}