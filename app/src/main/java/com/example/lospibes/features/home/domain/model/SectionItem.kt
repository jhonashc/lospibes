package com.example.lospibes.features.home.domain.model

import androidx.compose.ui.graphics.painter.Painter

data class SectionItem(
    val name: String,
    val description: String,
    val route: String,
    val icon: Painter
)
