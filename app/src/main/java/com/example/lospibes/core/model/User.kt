package com.example.lospibes.core.model

data class User(
    val id: String,
    val username: String,
    val email: String,
    val avatarUrl: String? = null,
    val roles: List<Role> = emptyList(),
    val person: Person? = null
)