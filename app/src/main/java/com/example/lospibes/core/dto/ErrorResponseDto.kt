package com.example.lospibes.core.dto

data class ErrorResponseDto(
    val status: Boolean = false,
    val message: Any /* Temporal */
)
