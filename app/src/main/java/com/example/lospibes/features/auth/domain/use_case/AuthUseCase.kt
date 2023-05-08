package com.example.lospibes.features.auth.domain.use_case

data class AuthUseCase(
    val login: Login,
    val register: Register,
    val refreshToken: RefreshToken
)
