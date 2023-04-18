package com.example.lospibes.features.home.data.source.remote

import com.example.lospibes.features.home.data.dto.response.CategoriesResponse
import com.example.lospibes.utils.Constants.CATEGORIES
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoryService {
    @GET(CATEGORIES)
    suspend fun getCategories(
        @Query("name") name: String? = null,
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null
    ): Response<CategoriesResponse>
}