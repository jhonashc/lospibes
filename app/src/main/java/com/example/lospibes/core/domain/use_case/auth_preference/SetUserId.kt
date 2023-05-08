package com.example.lospibes.core.domain.use_case.auth_preference

import com.example.lospibes.core.domain.repository.AuthPreferenceRepository

class SetUserId(
    private val authPreferenceRepository: AuthPreferenceRepository
) {
    suspend operator fun invoke(
        userId: String
    ) {
        return authPreferenceRepository.setUserId(
            userId = userId
        )
    }
}