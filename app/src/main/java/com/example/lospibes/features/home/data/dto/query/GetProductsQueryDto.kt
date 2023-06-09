package com.example.lospibes.features.home.data.dto.query

data class GetProductsQueryDto(
    val limit: Int? = null,
    val offset: Int? = null
)

data class GetSimilarProductsQueryDto(
    val limit: Int? = null,
    val offset: Int? = null
)

data class SearchProductsQueryDto(
    val name: String? = null,
    val category: String? = null,
    val min: Int? = null,
    val max: Int? = null,
    val limit: Int? = null,
    val offset: Int? = null
)