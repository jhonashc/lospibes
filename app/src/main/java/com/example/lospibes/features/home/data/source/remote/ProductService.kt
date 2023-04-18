package com.example.lospibes.features.home.data.source.remote

import com.example.lospibes.core.model.ApiResponse
import com.example.lospibes.features.home.domain.model.Product
import com.example.lospibes.utils.Constants.PRODUCT_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductService {
    @GET(PRODUCT_ENDPOINT)
    suspend fun getProducts(
        @Query("name") name: String? = null,
        @Query("category") category: String? = null
    ): Response<ApiResponse<List<Product>>>

    @GET("${PRODUCT_ENDPOINT}/{id}")
    suspend fun getProductById(
        @Path("id") id: String
    ): Response<ApiResponse<Product>>
}