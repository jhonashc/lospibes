package com.example.lospibes.features.auth.data.source.remote

import com.example.lospibes.features.auth.data.dto.body.CreateLoginDto
import com.example.lospibes.features.auth.data.dto.body.CreateRefreshTokenDto
import com.example.lospibes.features.auth.data.dto.body.CreateRegisterDto
import com.example.lospibes.features.auth.data.dto.body.CreateResendEmailVerificationDto
import com.example.lospibes.features.auth.data.dto.body.CreateVerifyAccountDto
import com.example.lospibes.features.auth.data.dto.response.LoginResponse
import com.example.lospibes.features.auth.data.dto.response.RefreshTokenResponse
import com.example.lospibes.features.auth.data.dto.response.RegisterResponse
import com.example.lospibes.features.auth.data.dto.response.ResendEmailVerificationResponse
import com.example.lospibes.features.auth.data.dto.response.VerifyEmailResponse
import com.example.lospibes.utils.Constants.AUTH
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("${AUTH}/login")
    suspend fun login(
        @Body createLoginDto: CreateLoginDto
    ): Response<LoginResponse>

    @POST("${AUTH}/register")
    suspend fun register(
        @Body createRegisterDto: CreateRegisterDto
    ): Response<RegisterResponse>

    @POST("${AUTH}/verify-account")
    suspend fun verifyAccount(
        @Body createVerifyAccountDto: CreateVerifyAccountDto
    ): Response<VerifyEmailResponse>

    @POST("${AUTH}/resend-email")
    suspend fun resendEmail(
        @Body createdResendEmailVerificationDto: CreateResendEmailVerificationDto
    ): Response<ResendEmailVerificationResponse>

    @POST("${AUTH}/refresh-token")
    suspend fun refreshToken(
        @Body createRefreshTokenDto: CreateRefreshTokenDto
    ): Response<RefreshTokenResponse>
}