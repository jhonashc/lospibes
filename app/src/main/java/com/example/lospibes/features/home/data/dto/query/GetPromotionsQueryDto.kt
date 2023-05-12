package com.example.lospibes.features.home.data.dto.query

import com.example.lospibes.features.home.domain.model.Days

data class GetPromotionsWithProductsQueryDto(
    val day: Days? = null,
    val limit: Int? = null,
    val offset: Int? = null
)