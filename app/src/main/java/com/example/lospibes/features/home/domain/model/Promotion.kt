package com.example.lospibes.features.home.domain.model

data class PromotionWithProducts(
    val id: String,
    val name: String,
    val description: String? = null,
    val discountPercentage: Int,
    val availableDay: Days,
    val products: List<Product>
)

enum class Days {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY
}