package com.example.lospibes.core.model

data class Person(
    val id: String,
    val firstName: String? = null,
    val lastName: String? = null,
    val telephone: String? = null,
    val gender: Gender? = null
)
