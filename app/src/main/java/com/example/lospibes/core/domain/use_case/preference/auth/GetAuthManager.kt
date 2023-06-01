package com.example.lospibes.core.domain.use_case.preference.auth

import com.example.lospibes.core.domain.model.Auth
import com.example.lospibes.core.domain.repository.AuthManagerRepository
import kotlinx.coroutines.flow.Flow

class GetAuthManager(
    private val authPreferenceRepository: AuthManagerRepository
) {
    operator fun invoke(): Flow<Auth> {
        return authPreferenceRepository.getAuthManager()
    }
}