package com.example.lospibes.features.home.domain.use_case.category

import com.example.lospibes.features.home.data.dto.query.GetCategoriesQueryDto
import com.example.lospibes.features.home.data.dto.response.CategoriesResponse
import com.example.lospibes.features.home.domain.repository.CategoryRepository
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class GetCategories(
    private val categoryRepository: CategoryRepository
) {
    operator fun invoke(
        getCategoriesQueryDto: GetCategoriesQueryDto? = null
    ): Flow<NetworkResult<CategoriesResponse>> {
        return categoryRepository.getCategories(getCategoriesQueryDto)
    }
}