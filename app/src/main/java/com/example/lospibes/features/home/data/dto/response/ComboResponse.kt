package com.example.lospibes.features.home.data.dto.response

import com.example.lospibes.features.home.domain.model.Combo

data class ComboResponse(
    val status: Boolean,
    val data: List<Combo>
)
