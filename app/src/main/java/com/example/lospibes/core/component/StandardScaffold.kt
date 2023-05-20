package com.example.lospibes.core.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun StandardScaffold(
    showBottomBar: Boolean = true,
    snackBarHost: @Composable () -> Unit = {},
    topAppBar: @Composable () -> Unit = {},
    bottomAppBar: @Composable () -> Unit = {},
    content: @Composable () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        snackbarHost = snackBarHost,
        topBar = topAppBar,
        bottomBar = {
            if (showBottomBar) {
                bottomAppBar()
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            content()
        }
    }
}