package com.example.lospibes.features.home.cart.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lospibes.common.components.StandardScaffold
import com.example.lospibes.common.components.StandardTopAppBar

@Composable
fun CartScreen() {
    StandardScaffold(
        topAppBar = {
            StandardTopAppBar(title = "Cart")
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier.padding(vertical = 20.dp)
            ) {
                Text(text = "Cart")
            }
        }
    }
}