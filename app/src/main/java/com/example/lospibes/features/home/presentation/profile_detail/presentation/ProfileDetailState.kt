package com.example.lospibes.features.home.presentation.profile_detail.presentation

import android.net.Uri
import com.example.lospibes.core.domain.model.User

data class ProfileDetailState(
    val status: Boolean = false,
    val message: String? = null,
    val isLoading: Boolean = true,
    val user: User? = null,
    val username: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val telephone: String = "",
    val selectedImageUri: Uri? = null
)
