package com.example.lospibes.features.home.domain.model

import java.util.Date

data class Category(
    val id: String,
    val name: String,
    val code: String,
    val createdAt: Date,
    val updatedAt: Date
)
