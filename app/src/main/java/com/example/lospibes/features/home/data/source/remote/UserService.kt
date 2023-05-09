package com.example.lospibes.features.home.data.source.remote

import com.example.lospibes.features.home.data.dto.response.UserResponse
import com.example.lospibes.utils.Constants.USERS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("${USERS}/{id}")
    suspend fun getUserById(
        @Path("id") id: String
    ): Response<UserResponse>
}