package com.example.lospibes.features.auth.domain.use_case

import com.example.lospibes.features.auth.data.dto.body.CreateResendEmailVerificationDto
import com.example.lospibes.features.auth.data.dto.response.ResendEmailVerificationResponse
import com.example.lospibes.features.auth.domain.repository.AuthRepository
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class ResendEmail(
    private val authRepository: AuthRepository
) {
    operator fun invoke(
        createResendEmailVerificationDto: CreateResendEmailVerificationDto
    ): Flow<NetworkResult<ResendEmailVerificationResponse>> {
        return authRepository.resendEmail(
            createResendEmailVerificationDto = createResendEmailVerificationDto
        )
    }
}