package com.example.lospibes.core.view_model.auth

sealed class AuthEvent {
    object GetAccessToken : AuthEvent()
    object GetRefreshToken : AuthEvent()
    object GetUserId : AuthEvent()
    data class SetAccessToken(val value: String) : AuthEvent()
    data class SetRefreshToken(val value: String) : AuthEvent()
    data class SetUserId(val value: String) : AuthEvent()
}