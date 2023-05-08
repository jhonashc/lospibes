package com.example.lospibes.core.domain.use_case.auth_preference

import com.example.lospibes.core.domain.repository.AuthPreferenceRepository
import kotlinx.coroutines.flow.Flow

class GetAccessToken(
    private val authPreferenceRepository: AuthPreferenceRepository
) {
    operator fun invoke(): Flow<String> {
        return authPreferenceRepository.getAccessToken()
    }
}