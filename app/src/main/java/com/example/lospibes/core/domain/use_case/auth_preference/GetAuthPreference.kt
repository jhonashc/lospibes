package com.example.lospibes.core.domain.use_case.auth_preference

import com.example.lospibes.core.domain.model.Auth
import com.example.lospibes.core.domain.repository.AuthPreferenceRepository
import kotlinx.coroutines.flow.Flow

class GetAuthPreference(
    private val authPreferenceRepository: AuthPreferenceRepository
) {
    operator fun invoke(): Flow<Auth> {
        return authPreferenceRepository.getAuthPreference()
    }
}