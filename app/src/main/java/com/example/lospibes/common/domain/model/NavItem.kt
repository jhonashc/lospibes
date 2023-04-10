package com.example.lospibes.common.domain.model

import androidx.compose.ui.graphics.painter.Painter

data class NavItem(
    val name: String,
    val route: String,
    val icon: Painter,
)
