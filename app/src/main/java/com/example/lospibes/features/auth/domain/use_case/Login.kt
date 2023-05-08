package com.example.lospibes.features.auth.domain.use_case

import com.example.lospibes.features.auth.data.dto.body.CreateLoginDto
import com.example.lospibes.features.auth.data.dto.response.AuthResponse
import com.example.lospibes.features.auth.domain.repository.AuthRepository
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class Login(
    private val authRepository: AuthRepository
) {
    operator fun invoke(
        createLoginDto: CreateLoginDto
    ): Flow<NetworkResult<AuthResponse>> {
        return authRepository.login(
            createLoginDto = createLoginDto
        )
    }
}