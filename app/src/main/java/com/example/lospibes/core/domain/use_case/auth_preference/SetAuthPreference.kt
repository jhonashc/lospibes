package com.example.lospibes.core.domain.use_case.auth_preference

import com.example.lospibes.core.domain.model.Auth
import com.example.lospibes.core.domain.repository.AuthPreferenceRepository

class SetAuthPreference(
    private val authPreferenceRepository: AuthPreferenceRepository
) {
    suspend operator fun invoke(
        auth: Auth
    ) {
        return authPreferenceRepository.setAuthPreference(
            auth = auth
        )
    }
}