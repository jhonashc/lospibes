package com.example.lospibes.features.auth.presentation.login.presentation

import com.example.lospibes.core.model.User

data class LoginState(
    val message: String? = null,
    val isLoading: Boolean = false,
    val email: String = "admin@gmail.com",
    val password: String = "password",
    val user: User? = null,
    val token: String? = null,
    val expiresIn: Int? = null
)
