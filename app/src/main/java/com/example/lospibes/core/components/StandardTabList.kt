package com.example.lospibes.core.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.lospibes.features.home.domain.model.TabItem

@Composable
fun StandardTabList(
    tabList: List<TabItem>,
    selectedTab: TabItem,
    showIcon: Boolean = false,
    onTabSelected: (tabItem: TabItem) -> Unit,
) {
    ScrollableTabRow(
        selectedTabIndex = tabList.indexOf(selectedTab),
        contentColor = MaterialTheme.colorScheme.onSurface,
        edgePadding = 8.dp,
        indicator = {},
        divider = {}
    ) {
        tabList.forEach { tab ->
            StandardTab(
                tabItem = tab,
                isSelected = selectedTab.name == tab.name,
                showIcon = showIcon,
                onClick = { onTabSelected(tab) }
            )
        }
    }
}