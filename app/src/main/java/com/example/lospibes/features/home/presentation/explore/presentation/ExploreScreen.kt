package com.example.lospibes.features.home.presentation.explore.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lospibes.core.components.StandardTopBar

@Composable
fun ExploreScreen(
    onNavigateToHome: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier.padding(bottom = 20.dp)
        ) {
            Header(
                onNavigateToHome = onNavigateToHome
            )
        }
    }
}

@Composable
private fun Header(
    onNavigateToHome: () -> Unit
) {
    StandardTopBar(
        onBackTo = onNavigateToHome
    )
}