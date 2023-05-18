package com.example.lospibes.features.auth.domain.use_case

import com.example.lospibes.features.auth.data.dto.body.CreateLoginDto
import com.example.lospibes.features.auth.data.dto.response.LoginResponse
import com.example.lospibes.features.auth.domain.repository.AuthRepository
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class Login(
    private val authRepository: AuthRepository
) {
    operator fun invoke(
        createLoginDto: CreateLoginDto
    ): Flow<NetworkResult<LoginResponse>> {
        return authRepository.login(
            createLoginDto = createLoginDto
        )
    }
}