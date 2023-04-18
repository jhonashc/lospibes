package com.example.lospibes.features.home.data.source.remote

import com.example.lospibes.features.home.data.dto.response.ProductResponse
import com.example.lospibes.features.home.data.dto.response.ProductsResponse
import com.example.lospibes.utils.Constants.PRODUCTS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductService {
    @GET(PRODUCTS)
    suspend fun getProducts(
        @Query("name") name: String? = null,
        @Query("category") category: String? = null,
        @Query("min") min: Int? = null,
        @Query("max") max: Int? = null,
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null
    ): Response<ProductsResponse>

    @GET("${PRODUCTS}/{id}")
    suspend fun getProductById(
        @Path("id") id: String
    ): Response<ProductResponse>
}