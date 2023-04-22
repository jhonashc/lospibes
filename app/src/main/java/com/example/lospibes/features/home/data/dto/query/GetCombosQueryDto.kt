package com.example.lospibes.features.home.data.dto.query

data class GetCombosQueryDto(
    val name: String? = null,
    val min: Int? = null,
    val max: Int? = null,
    val limit: Int? = null,
    val offset: Int? = null
)
