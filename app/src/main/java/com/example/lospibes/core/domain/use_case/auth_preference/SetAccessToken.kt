package com.example.lospibes.core.domain.use_case.auth_preference

import com.example.lospibes.core.domain.repository.AuthPreferenceRepository

class SetAccessToken(
    private val authPreferenceRepository: AuthPreferenceRepository
) {
    suspend operator fun invoke(
        accessToken: String
    ) {
        return authPreferenceRepository.setAccessToken(accessToken)
    }
}