package com.example.lospibes.core.model

data class ApiResponse<T>(
    val status: Boolean,
    val data: T? = null
)