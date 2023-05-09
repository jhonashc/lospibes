package com.example.lospibes.features.home.presentation.profile.presentation

import com.example.lospibes.core.domain.model.User

data class ProfileState(
    val status: Boolean = false,
    val message: String? = null,
    val isLoading: Boolean = true,
    val user: User? = null
)
