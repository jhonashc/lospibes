package com.example.lospibes.features.auth.domain.use_case

import com.example.lospibes.features.auth.data.dto.body.CreateVerifyAccountDto
import com.example.lospibes.features.auth.data.dto.response.VerifyEmailResponse
import com.example.lospibes.features.auth.domain.repository.AuthRepository
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class VerifyAccount(
    private val authRepository: AuthRepository
) {
    operator fun invoke(
        createVerifyAccountDto: CreateVerifyAccountDto
    ): Flow<NetworkResult<VerifyEmailResponse>> {
        return authRepository.verifyAccount(createVerifyAccountDto)
    }
}