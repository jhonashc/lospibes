package com.example.lospibes.features.auth.domain.use_case

import com.example.lospibes.features.auth.data.dto.body.CreateRegisterDto
import com.example.lospibes.features.auth.data.dto.response.RegisterResponse
import com.example.lospibes.features.auth.domain.repository.AuthRepository
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class Register(
    private val authRepository: AuthRepository
) {
    operator fun invoke(
        createRegisterDto: CreateRegisterDto
    ): Flow<NetworkResult<RegisterResponse>> {
        return authRepository.register(
            createRegisterDto = createRegisterDto
        )
    }
}