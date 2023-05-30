package com.example.lospibes.features.home.domain.model

data class Promotion(
    val id: String,
    val name: String,
    val description: String? = null,
    val discountPercentage: Int,
    val availableDay: String
)