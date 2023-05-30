package com.example.lospibes.features.home.data.dto.query

data class GetPromotionsQueryDto(
    val day: String? = null,
    val limit: Int? = null,
    val offset: Int? = null
)