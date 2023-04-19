package com.radin.data.remote.dtos

import com.google.gson.annotations.SerializedName
import com.radin.domain.models.Flags
import com.radin.domain.utils.Mapper

data class FlagsDto(
    @SerializedName("svg")
    val svg: String,
    @SerializedName("png")
    val png: String
): Mapper<Flags> {

    override fun toDomain() = Flags(
        component1(),
        component2()
    )
}