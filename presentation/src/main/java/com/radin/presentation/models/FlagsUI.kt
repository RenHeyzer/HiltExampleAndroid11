package com.radin.presentation.models

import com.radin.domain.models.Flags

data class FlagsUI(
    val svg: String,
    val png: String
)

fun Flags.toUI() = FlagsUI(
    svg, png
)