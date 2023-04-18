package com.example.lospibes.features.home.domain.repository

import com.example.lospibes.features.home.data.dto.query.GetCategoriesQueryDto
import com.example.lospibes.features.home.data.dto.response.CategoriesResponse
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategories(
        getCategoriesQueryDto: GetCategoriesQueryDto? = null
    ): Flow<NetworkResult<CategoriesResponse>>
}