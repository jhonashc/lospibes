package com.example.lospibes.core.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.lospibes.features.home.domain.model.SearchItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardCenterTopBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit = {},
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable (RowScope.() -> Unit) = {}
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = title,
        navigationIcon = navigationIcon,
        actions = actions
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardSearchTopBar(
    query: String = "",
    isActive: Boolean = false,
    searchResultList: List<SearchItem> = emptyList(),
    onClose: () -> Unit,
    onItemClick: (id: String) -> Unit,
    onSearch: (newQuery: String) -> Unit,
    onQueryChange: (newQuery: String) -> Unit,
    onActiveChange: (newActive: Boolean) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(key1 = Unit) {
        focusRequester.requestFocus()
    }

    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp)
            .focusRequester(focusRequester),
        query = query,
        shape = MaterialTheme.shapes.extraSmall,
        tonalElevation = 0.dp,
        placeholder = {
            Text(
                text = "Buscar",
                style = MaterialTheme.typography.titleMedium
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search Icon",
                tint = MaterialTheme.colorScheme.outline
            )
        },
        trailingIcon = {
            IconButton(
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = MaterialTheme.colorScheme.outline
                ),
                onClick = onClose
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Close Icon"
                )
            }
        },
        onQueryChange = onQueryChange,
        onSearch = { searchValue ->
            if (searchValue.isNotEmpty()) {
                onSearch(searchValue)
                focusManager.clearFocus()
            }
        },
        active = isActive,
        onActiveChange = onActiveChange
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            searchResultList.forEach { searchItem ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onItemClick(searchItem.id) }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.weight(1f),
                            text = searchItem.name,
                            style = MaterialTheme.typography.titleMedium,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )

                        Icon(
                            imageVector = Icons.Filled.ArrowForward,
                            contentDescription = "ArrowForward Icon"
                        )
                    }
                }
            }
        }
    }
}