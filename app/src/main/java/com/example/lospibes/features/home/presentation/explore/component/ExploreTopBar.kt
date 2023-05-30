package com.example.lospibes.features.home.presentation.explore.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.lospibes.R
import com.example.lospibes.core.component.StandardCenterTopBar
import com.example.lospibes.core.component.StandardSearchTopBar
import com.example.lospibes.features.home.domain.model.SearchItem
import com.example.lospibes.features.home.domain.model.SearchWidgetState

@Composable
fun ExploreTopBar(
    query: String,
    isActive: Boolean,
    searchResultList: List<SearchItem>,
    searchWidgetState: SearchWidgetState,
    onNavigateToHome: () -> Unit,
    onSearchClick: () -> Unit,
    onFilterClick: () -> Unit,
    onClose: () -> Unit,
    onItemClick: (id: String) -> Unit,
    onSearch: (newQuery: String) -> Unit,
    onQueryChange: (newQuery: String) -> Unit,
    onActiveChange: (newActive: Boolean) -> Unit
) {
    when (searchWidgetState) {
        SearchWidgetState.CLOSED -> {
            DefaultExploreTopBar(
                onSearchClick = onSearchClick,
                onFilterClick = onFilterClick,
                onNavigateToHome = onNavigateToHome
            )
        }

        SearchWidgetState.OPENED -> {
            ExploreSearchTopBar(
                query = query,
                isActive = isActive,
                searchResultList = searchResultList,
                onClose = onClose,
                onItemClick = onItemClick,
                onSearch = onSearch,
                onQueryChange = onQueryChange,
                onActiveChange = onActiveChange
            )
        }
    }
}

@Composable
private fun DefaultExploreTopBar(
    onSearchClick: () -> Unit,
    onFilterClick: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    StandardCenterTopBar(
        title = {
            Text(
                text = "Explorar",
                style = MaterialTheme.typography.titleMedium
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onNavigateToHome
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "Back Icon"
                )
            }
        },
        actions = {
            IconButton(
                onClick = onFilterClick
            ) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.baseline_filter_alt_24
                    ),
                    contentDescription = "Filter Icon"
                )
            }

            IconButton(
                onClick = onSearchClick
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search Icon"
                )
            }
        }
    )
}

@Composable
private fun ExploreSearchTopBar(
    query: String,
    isActive: Boolean,
    searchResultList: List<SearchItem>,
    onClose: () -> Unit,
    onItemClick: (id: String) -> Unit,
    onSearch: (newQuery: String) -> Unit,
    onQueryChange: (newQuery: String) -> Unit,
    onActiveChange: (newActive: Boolean) -> Unit
) {
    StandardSearchTopBar(
        query = query,
        isActive = isActive,
        searchResultList = searchResultList,
        onClose = onClose,
        onItemClick = onItemClick,
        onSearch = onSearch,
        onQueryChange = onQueryChange,
        onActiveChange = onActiveChange
    )
}