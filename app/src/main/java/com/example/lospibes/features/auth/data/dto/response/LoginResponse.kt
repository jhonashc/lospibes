package com.example.lospibes.features.auth.data.dto.response

import com.example.lospibes.core.domain.model.User

data class LoginResponse(
    val status: Boolean = true,
    val user: User,
    val token: String,
    val expiresIn: Int
)
