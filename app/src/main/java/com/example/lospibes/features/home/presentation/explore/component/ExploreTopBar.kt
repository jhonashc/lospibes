package com.example.lospibes.features.home.presentation.explore.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lospibes.core.component.StandardSearchTopBar
import com.example.lospibes.core.component.StandardTopBar
import com.example.lospibes.features.home.domain.model.SearchWidgetState

@Composable
fun ExploreTopBar(
    searchText: String,
    searchResultList: List<String>,
    searchWidgetState: SearchWidgetState,
    onNavigateToHome: () -> Unit,
    onSearchClick: () -> Unit,
    onClose: () -> Unit,
    onSubmit: (value: String) -> Unit,
    onValueChange: (newValue: String) -> Unit
) {
    when (searchWidgetState) {
        SearchWidgetState.CLOSED -> {
            DefaultExploreTopBar(
                onSearchClick = onSearchClick,
                onNavigateToHome = onNavigateToHome
            )
        }

        SearchWidgetState.OPENED -> {
            ExploreSearchTopBar(
                value = searchText,
                searchResultList = searchResultList,
                onClose = onClose,
                onSubmit = onSubmit,
                onValueChange = onValueChange
            )
        }
    }
}

@Composable
private fun DefaultExploreTopBar(
    onSearchClick: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    StandardTopBar(
        title = "Explorar",
        navigationIcon = {
            Icon(
                imageVector = Icons.Outlined.ArrowBack,
                contentDescription = "Back Icon"
            )
        },
        actions = {
            IconButton(
                modifier = Modifier.padding(end = 5.dp),
                onClick = onSearchClick
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search Icon"
                )
            }
        },
        onBackTo = onNavigateToHome
    )
}

@Composable
private fun ExploreSearchTopBar(
    value: String,
    searchResultList: List<String>,
    onClose: () -> Unit,
    onSubmit: (value: String) -> Unit,
    onValueChange: (newValue: String) -> Unit
) {
    StandardSearchTopBar(
        value = value,
        searchResultList = searchResultList,
        onSubmit = onSubmit,
        onClose = onClose,
        onValueChange = onValueChange
    )
}