package com.example.lospibes.core.domain.model

data class Auth(
    val accessToken: String,
    val refreshToken: String,
    val userId: String
)
