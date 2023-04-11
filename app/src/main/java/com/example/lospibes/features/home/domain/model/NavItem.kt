package com.example.lospibes.features.home.domain.model

import androidx.compose.ui.graphics.painter.Painter

data class NavItem(
    val name: String,
    val route: String,
    val icon: Painter,
)
