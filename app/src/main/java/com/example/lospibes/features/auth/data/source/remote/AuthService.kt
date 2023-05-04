package com.example.lospibes.features.auth.data.source.remote

import com.example.lospibes.features.auth.data.dto.body.CreateLoginDto
import com.example.lospibes.features.auth.data.dto.response.LoginResponse
import com.example.lospibes.utils.Constants.AUTH
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("${AUTH}/login")
    suspend fun login(
        @Body createLoginDto: CreateLoginDto
    ): Response<LoginResponse>
}