package com.example.lospibes.core.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun StandardColumnContainer(
    isLoading: Boolean,
    message: String?,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (isLoading) {
            CircularProgressIndicator()
        }

        if (!isLoading && message != null) {
            Text(
                text = message,
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )
        }

        if (!isLoading && message.isNullOrBlank()) {
            content()
        }
    }
}

@Composable
fun StandardScrollableColumnContainer(
    isLoading: Boolean,
    message: String?,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }

        if (!isLoading && message != null) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = message,
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )
        }

        if (!isLoading && message.isNullOrBlank()) {
            Column(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .fillMaxSize()
            ) {
                content()
            }
        }
    }
}