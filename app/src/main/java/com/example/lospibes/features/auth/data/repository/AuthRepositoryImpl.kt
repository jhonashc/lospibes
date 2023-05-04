package com.example.lospibes.features.auth.data.repository

import com.example.lospibes.features.auth.data.dto.body.CreateLoginDto
import com.example.lospibes.features.auth.data.dto.response.LoginResponse
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
}