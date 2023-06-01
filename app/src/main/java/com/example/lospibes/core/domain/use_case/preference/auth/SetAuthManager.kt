package com.example.lospibes.core.domain.use_case.preference.auth

import com.example.lospibes.core.domain.model.Auth
import com.example.lospibes.core.domain.repository.AuthManagerRepository

class SetAuthManager(
    private val authPreferenceRepository: AuthManagerRepository
) {
    suspend operator fun invoke(auth: Auth) {
        return authPreferenceRepository.setAuthManager(auth)
    }
}