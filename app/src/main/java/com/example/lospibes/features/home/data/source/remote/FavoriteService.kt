package com.example.lospibes.features.home.data.source.remote

import com.example.lospibes.features.home.data.dto.response.FavoriteComboResponse
import com.example.lospibes.features.home.data.dto.response.FavoriteProductResponse
import com.example.lospibes.utils.Constants.FAVORITES
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FavoriteService {
    @GET("${FAVORITES}/{userId}/combos")
    fun getFavoriteCombos(
        @Path("userId") userId: String
    ): Response<FavoriteComboResponse>

    @GET("${FAVORITES}/{userId}/products")
    fun getFavoriteProducts(
        @Path("userId") userId: String
    ): Response<FavoriteProductResponse>
}