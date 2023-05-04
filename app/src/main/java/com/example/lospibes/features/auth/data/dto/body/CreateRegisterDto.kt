package com.example.lospibes.features.auth.data.dto.body

import com.example.lospibes.core.model.Person

data class CreateRegisterDto(
    val person: Person? = null,
    val username: String,
    val email: String,
    val password: String,
    val avatarUrl: String? = null
)
