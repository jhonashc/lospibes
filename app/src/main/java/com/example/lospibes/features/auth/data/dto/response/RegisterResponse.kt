package com.example.lospibes.features.auth.data.dto.response

import com.example.lospibes.core.domain.model.User

data class RegisterResponse(
    val message: String,
    val data: User
)
