package com.example.lospibes.core.view_model.auth

import com.example.lospibes.core.domain.model.Auth

sealed class AuthEvent {
    object GetAuthState : AuthEvent()
    data class SetAuthState(val value: Auth) : AuthEvent()
}
