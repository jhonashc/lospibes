package com.example.lospibes.features.home.data.source.remote

import com.example.lospibes.features.home.data.dto.response.FavoriteCombosResponse
import com.example.lospibes.features.home.data.dto.response.FavoriteProductsResponse
import com.example.lospibes.utils.Constants.FAVORITES
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FavoriteService {
    @GET("${FAVORITES}/{userId}/combos")
    suspend fun getFavoriteCombos(
        @Path("userId") userId: String
    ): Response<FavoriteCombosResponse>

    @GET("${FAVORITES}/{userId}/products")
    suspend fun getFavoriteProducts(
        @Path("userId") userId: String
    ): Response<FavoriteProductsResponse>
}