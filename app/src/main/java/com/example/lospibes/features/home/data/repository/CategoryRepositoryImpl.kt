package com.example.lospibes.features.home.data.repository

import com.example.lospibes.features.home.data.dto.query.GetCategoriesQueryDto
import com.example.lospibes.features.home.data.dto.response.CategoriesResponse
import com.example.lospibes.features.home.data.source.remote.CategoryService
import com.example.lospibes.features.home.domain.repository.CategoryRepository
import com.example.lospibes.utils.BaseApiResponse
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryService: CategoryService
) : CategoryRepository, BaseApiResponse() {
    override fun getCategories(
        getCategoriesQueryDto: GetCategoriesQueryDto?
    ): Flow<NetworkResult<CategoriesResponse>> {
        return safeApiCall {
            categoryService.getCategories(
                name = getCategoriesQueryDto?.name,
                limit = getCategoriesQueryDto?.limit,
                offset = getCategoriesQueryDto?.offset
            )
        }
    }
}