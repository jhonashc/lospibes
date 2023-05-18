package com.example.lospibes.features.auth.domain.repository

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
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(
        createLoginDto: CreateLoginDto
    ): Flow<NetworkResult<LoginResponse>>

    fun register(
        createRegisterDto: CreateRegisterDto
    ): Flow<NetworkResult<RegisterResponse>>

    fun verifyAccount(
        createVerifyAccountDto: CreateVerifyAccountDto
    ): Flow<NetworkResult<VerifyEmailResponse>>

    fun resendEmail(
        createResendEmailVerificationDto: CreateResendEmailVerificationDto
    ): Flow<NetworkResult<ResendEmailVerificationResponse>>

    fun refreshToken(
        createRefreshTokenDto: CreateRefreshTokenDto
    ): Flow<NetworkResult<RefreshTokenResponse>>
}