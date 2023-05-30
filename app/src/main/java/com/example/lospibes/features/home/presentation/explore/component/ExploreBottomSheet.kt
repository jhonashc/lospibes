package com.example.lospibes.features.home.presentation.explore.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lospibes.core.component.StandardBottomSheet
import com.example.lospibes.core.component.StandardFlowRow
import com.example.lospibes.features.home.data.dto.query.SearchProductsQueryDto
import com.example.lospibes.features.home.presentation.explore.presentation.ExploreBottomEvent
import com.example.lospibes.features.home.presentation.explore.presentation.ExploreBottomViewModel
import com.example.lospibes.features.home.presentation.explore.presentation.ExploreEvent
import com.example.lospibes.features.home.presentation.explore.presentation.ExploreViewModel

@Composable
fun ExploreBottomSheet(
    exploreViewModel: ExploreViewModel,
    exploreBottomViewModel: ExploreBottomViewModel = hiltViewModel(),
    onDismissRequest: () -> Unit
) {
    val exploreBottomSheetState = exploreBottomViewModel.state.collectAsState()

    StandardBottomSheet(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        onDismissRequest = {
            onDismissRequest()

//            if (exploreBottomSheetState.value.category.isNotEmpty() &&
//                exploreBottomSheetState.value.range != exploreBottomSheetState.value.valueRange
//            ) {
//                exploreViewModel.onEvent(ExploreEvent.OnResetQuery)
//                exploreBottomViewModel.onEvent(ExploreBottomEvent.OnResetQuery)
//            }
        }
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            if (exploreBottomSheetState.value.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                Column(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .fillMaxWidth()
                ) {
                    Header(
                        exploreViewModel = exploreViewModel,
                        exploreBottomViewModel = exploreBottomViewModel
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Body(
                        exploreBottomViewModel = exploreBottomViewModel
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Footer(
                        exploreViewModel = exploreViewModel,
                        exploreBottomViewModel = exploreBottomViewModel,
                        onDismissRequest = onDismissRequest
                    )
                }
            }
        }
    }
}

@Composable
private fun Header(
    exploreViewModel: ExploreViewModel,
    exploreBottomViewModel: ExploreBottomViewModel
) {
    val exploreBottomState = exploreBottomViewModel.state.collectAsState()

    val isEnable = exploreBottomState.value.category.isNotEmpty() ||
            exploreBottomState.value.range != exploreBottomState.value.valueRange

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Filtros de búsqueda",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        TextButton(
            modifier = Modifier.align(Alignment.CenterEnd),
            enabled = isEnable,
            onClick = {
                exploreViewModel.onEvent(ExploreEvent.OnResetQuery)
                exploreBottomViewModel.onEvent(ExploreBottomEvent.OnResetQuery)
            }
        ) {
            Text(
                text = "Reset",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
private fun Body(
    exploreBottomViewModel: ExploreBottomViewModel
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        PriceSection(
            exploreBottomViewModel = exploreBottomViewModel
        )

        Spacer(modifier = Modifier.height(24.dp))

        CategorySection(
            exploreBottomViewModel = exploreBottomViewModel
        )
    }
}

@Composable
private fun PriceSection(
    exploreBottomViewModel: ExploreBottomViewModel
) {
    val exploreBottomSheetState = exploreBottomViewModel.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Rango de precio",
            style = MaterialTheme.typography.titleMedium
        )

        ExplorePriceSlider(
            value = exploreBottomSheetState.value.range,
            valueRange = exploreBottomSheetState.value.valueRange,
            steps = 4,
            onValueChange = {
                exploreBottomViewModel.onEvent(ExploreBottomEvent.EnteredRange(it))
            }
        )
    }
}

@Composable
private fun CategorySection(
    exploreBottomViewModel: ExploreBottomViewModel
) {
    val exploreBottomSheetState = exploreBottomViewModel.state.collectAsState()

    val categoryNameList: List<String> = exploreBottomSheetState.value.categoryList.map {
        it.name
    }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Categorías",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(14.dp))

        StandardFlowRow(
            itemList = categoryNameList,
            selectedItem = exploreBottomSheetState.value.category,
            onItemSelected = {
                exploreBottomViewModel.onEvent(ExploreBottomEvent.EnteredCategory(it))
            }
        )
    }
}

@Composable
private fun Footer(
    exploreViewModel: ExploreViewModel,
    exploreBottomViewModel: ExploreBottomViewModel,
    onDismissRequest: () -> Unit
) {
    val exploreBottomSheetState = exploreBottomViewModel.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = MaterialTheme.shapes.extraSmall,
            onClick = {
                onDismissRequest()

                val category = exploreBottomSheetState.value.category.ifEmpty { null }
                val min = exploreBottomSheetState.value.range.start.toInt()
                val max = exploreBottomSheetState.value.range.endInclusive.toInt()

                exploreViewModel.onEvent(
                    ExploreEvent.EnteredQuery(
                        SearchProductsQueryDto(
                            category = category,
                            min = min,
                            max = max
                        )
                    )
                )
            }
        ) {
            Text(
                text = "Aplicar",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}