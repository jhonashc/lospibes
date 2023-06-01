package com.example.lospibes.features.home.presentation.profile.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lospibes.features.home.domain.model.SectionItem

@Composable
fun SectionList(
    sectionList: List<SectionItem>,
    onSectionSelected: (selectedSectionItem: SectionItem) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        sectionList.forEach { sectionItem ->
            SectionItem(
                sectionItem = sectionItem,
                onClick = { onSectionSelected(sectionItem) }
            )
        }
    }
}