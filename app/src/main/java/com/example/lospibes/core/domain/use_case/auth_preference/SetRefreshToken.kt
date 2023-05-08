package com.example.lospibes.core.domain.use_case.auth_preference

import com.example.lospibes.core.domain.repository.AuthPreferenceRepository

class SetRefreshToken(
    private val authPreferenceRepository: AuthPreferenceRepository
) {
    suspend operator fun invoke(
        refreshToken: String
    ) {
        return authPreferenceRepository.setRefreshToken(
            refreshToken = refreshToken
        )
    }
}