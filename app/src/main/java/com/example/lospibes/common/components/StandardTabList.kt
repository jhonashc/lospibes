package com.example.lospibes.common.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.lospibes.common.domain.model.TabItem

@Composable
fun StandardTabList(
    tabList: List<TabItem>,
    selectedTab: TabItem,
    onTabSelected: (tabItem: TabItem) -> Unit,
    onNavigateTo: () -> Unit
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
                onClick = {
                    onTabSelected(tab)
                    onNavigateTo()
                }
            )
        }
    }
}