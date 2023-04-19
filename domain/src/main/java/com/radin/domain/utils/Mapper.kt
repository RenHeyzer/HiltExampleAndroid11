package com.radin.domain.utils

interface Mapper<T> {
    fun toDomain(): T
}