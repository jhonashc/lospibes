package com.example.lospibes.common.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardScaffold(
    bottomAppBar: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Scaffold(
        bottomBar = {
            bottomAppBar()
        },
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            content()
        }
    }
}