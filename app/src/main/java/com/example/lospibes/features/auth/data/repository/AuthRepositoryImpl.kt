package com.example.lospibes.features.auth.data.repository

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
import com.example.lospibes.features.auth.data.source.remote.AuthService
import com.example.lospibes.features.auth.domain.repository.AuthRepository
import com.example.lospibes.utils.BaseApiResponse
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService
) : AuthRepository, BaseApiResponse() {
    override fun login(
        createLoginDto: CreateLoginDto
    ): Flow<NetworkResult<LoginResponse>> {
        return safeApiCall { authService.login(createLoginDto) }
    }

    override fun register(
        createRegisterDto: CreateRegisterDto
    ): Flow<NetworkResult<RegisterResponse>> {
        return safeApiCall { authService.register(createRegisterDto) }
    }

    override fun verifyAccount(
        createVerifyAccountDto: CreateVerifyAccountDto
    ): Flow<NetworkResult<VerifyEmailResponse>> {
        return safeApiCall { authService.verifyAccount(createVerifyAccountDto) }
    }

    override fun resendEmail(
        createResendEmailVerificationDto: CreateResendEmailVerificationDto
    ): Flow<NetworkResult<ResendEmailVerificationResponse>> {
        return safeApiCall { authService.resendEmail(createResendEmailVerificationDto) }
    }

    override fun refreshToken(
        createRefreshTokenDto: CreateRefreshTokenDto
    ): Flow<NetworkResult<RefreshTokenResponse>> {
        return safeApiCall { authService.refreshToken(createRefreshTokenDto) }
    }
}