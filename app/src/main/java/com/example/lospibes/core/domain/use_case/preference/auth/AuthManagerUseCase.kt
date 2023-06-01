package com.example.lospibes.core.domain.use_case.preference.auth

data class AuthManagerUseCase(
    val getAuthManager: GetAuthManager,
    val setAuthManager: SetAuthManager,
    val deleteAuthManager: DeleteAuthManager
)
