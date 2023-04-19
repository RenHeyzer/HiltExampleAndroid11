package com.radin.presentation.models

import com.radin.domain.models.Country

data class CountryUI(
    val flags: FlagsUI,
    val name: String,
)

fun Country.toUI() : CountryUI {
    return CountryUI(
        flags.toUI(),
        name
    )
}