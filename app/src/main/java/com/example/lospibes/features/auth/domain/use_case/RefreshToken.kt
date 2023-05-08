package com.example.lospibes.features.auth.domain.use_case

import com.example.lospibes.features.auth.data.dto.body.CreateRefreshTokenDto
import com.example.lospibes.features.auth.data.dto.response.RefreshTokenResponse
import com.example.lospibes.features.auth.domain.repository.AuthRepository
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class RefreshToken(
    private val authRepository: AuthRepository
) {
    operator fun invoke(
        createRefreshTokenDto: CreateRefreshTokenDto
    ): Flow<NetworkResult<RefreshTokenResponse>> {
        return authRepository.refreshToken(createRefreshTokenDto)
    }
}