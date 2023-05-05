package com.example.lospibes.core.view_model.auth

sealed class AuthEvent {
    data class SetToken(val value: String): AuthEvent()
    data class SetUserId(val value: String): AuthEvent()
}