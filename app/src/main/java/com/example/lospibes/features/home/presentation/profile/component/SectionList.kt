package com.example.lospibes.features.home.presentation.profile.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lospibes.features.home.domain.model.SectionItem

@Composable
fun SectionList(
    sectionList: List<SectionItem>,
    onSectionSelected: (selectedSectionItem: SectionItem) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            sectionList.forEach { sectionItem ->
                SectionItem(
                    sectionItem = sectionItem,
                    onClick = { onSectionSelected(sectionItem) }
                )
            }
        }
    }
}