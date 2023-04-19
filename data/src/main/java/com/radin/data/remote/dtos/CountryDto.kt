package com.radin.data.remote.dtos

import com.google.gson.annotations.SerializedName
import com.radin.domain.models.Country
import com.radin.domain.utils.Mapper

data class CountryDto(
    @SerializedName("flags")
    val flags: FlagsDto,
    @SerializedName("name")
    val name: String,
): Mapper<Country> {

    override fun toDomain() = Country(
        component1().toDomain(),
        component2()
    )
}