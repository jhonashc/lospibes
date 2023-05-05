package com.example.lospibes.core.domain.model

data class User(
    val id: String,
    val username: String,
    val email: String,
    val avatarUrl: String? = null,
    val person: Person? = null
)