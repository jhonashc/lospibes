package com.example.lospibes.features.auth.data.dto.response

import com.example.lospibes.core.domain.model.User

data class AuthResponse(
    val user: User,
    val token: String,
    val expiresIn: Int
)
