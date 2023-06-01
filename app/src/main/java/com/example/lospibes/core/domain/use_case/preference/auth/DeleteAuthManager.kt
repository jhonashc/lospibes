package com.example.lospibes.core.domain.use_case.preference.auth

import com.example.lospibes.core.domain.repository.AuthManagerRepository

class DeleteAuthManager(
    private val authPreferenceRepository: AuthManagerRepository
) {
    suspend operator fun invoke() {
        return authPreferenceRepository.deleteAuthManager()
    }
}