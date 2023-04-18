package com.example.lospibes.features.home.data.dto.response

import com.example.lospibes.features.home.domain.model.Category

data class CategoriesResponse(
    val status: Boolean,
    val data: List<Category>
)
