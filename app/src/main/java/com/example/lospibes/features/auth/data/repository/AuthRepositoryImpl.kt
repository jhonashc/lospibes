package com.example.lospibes.features.auth.data.repository

import com.example.lospibes.features.auth.data.dto.body.CreateLoginDto
import com.example.lospibes.features.auth.data.dto.body.CreateRefreshTokenDto
import com.example.lospibes.features.auth.data.dto.body.CreateRegisterDto
import com.example.lospibes.features.auth.data.dto.response.AuthResponse
import com.example.lospibes.features.auth.data.dto.response.RefreshTokenResponse
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
    ): Flow<NetworkResult<AuthResponse>> {
        return safeApiCall { authService.login(createLoginDto) }
    }

    override fun register(
        createRegisterDto: CreateRegisterDto
    ): Flow<NetworkResult<AuthResponse>> {
        return safeApiCall { authService.register(createRegisterDto) }
    }

    override fun refreshToken(
        createRefreshTokenDto: CreateRefreshTokenDto
    ): Flow<NetworkResult<RefreshTokenResponse>> {
        return safeApiCall { authService.refreshToken(createRefreshTokenDto) }
    }
}