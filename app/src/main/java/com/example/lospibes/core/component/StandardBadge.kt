package com.example.lospibes.core.component

import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardBadge(
    badgeNumber: Int,
    icon: @Composable () -> Unit
) {
    BadgedBox(
        badge = {
            Badge {
                Text(
                    "$badgeNumber",
                    modifier = Modifier.semantics {
                        contentDescription =
                            "$badgeNumber new notifications"
                    }
                )
            }
        }) {
        icon()
    }
}