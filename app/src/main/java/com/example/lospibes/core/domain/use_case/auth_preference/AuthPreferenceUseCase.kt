package com.example.lospibes.core.domain.use_case.auth_preference

data class AuthPreferenceUseCase(
    val getAccessToken: GetAccessToken,
    val getRefreshToken: GetRefreshToken,
    val getUserId: GetUserId,
    val setAccessToken: SetAccessToken,
    val setRefreshToken: SetRefreshToken,
    val setUserId: SetUserId
)
