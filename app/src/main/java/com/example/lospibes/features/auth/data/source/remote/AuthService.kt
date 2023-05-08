package com.example.lospibes.features.auth.data.source.remote

import com.example.lospibes.features.auth.data.dto.body.CreateLoginDto
import com.example.lospibes.features.auth.data.dto.body.CreateRefreshTokenDto
import com.example.lospibes.features.auth.data.dto.body.CreateRegisterDto
import com.example.lospibes.features.auth.data.dto.response.AuthResponse
import com.example.lospibes.features.auth.data.dto.response.RefreshTokenResponse
import com.example.lospibes.utils.Constants.AUTH
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("${AUTH}/login")
    suspend fun login(
        @Body createLoginDto: CreateLoginDto
    ): Response<AuthResponse>

    @POST("${AUTH}/register")
    suspend fun register(
        @Body createRegisterDto: CreateRegisterDto
    ): Response<AuthResponse>

    @POST("${AUTH}/refresh-token")
    suspend fun refreshToken(
        @Body createRefreshTokenDto: CreateRefreshTokenDto
    ): Response<RefreshTokenResponse>
}