package com.asmitta.talkin.utils.mapper.core

interface Mapper<I, O> {
    fun map(input: I): O
}