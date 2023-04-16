package com.example.lospibes.features.home.presentation.filter.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lospibes.core.components.StandardFlowRow
import com.example.lospibes.core.components.StandardTopBar
import com.example.lospibes.utils.Constants.categories

@Composable
fun FilterScreen(
    onNavigateToExplore: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier.padding(vertical = 20.dp)
        ) {
            Header(
                onNavigateToExplore = onNavigateToExplore
            )
        }
    }
}

@Composable
private fun Header(
    onNavigateToExplore: () -> Unit
) {
    StandardTopBar(
        onBackTo = onNavigateToExplore
    )
}