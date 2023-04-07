package com.example.lospibes.common.domain.model

import java.util.Date

data class Category(
    val id: String,
    val name: String,
    val code: String,
    val createdAt: Date,
    val updatedAt: Date
)
