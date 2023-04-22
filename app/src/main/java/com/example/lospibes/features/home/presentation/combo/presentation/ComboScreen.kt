package com.example.lospibes.features.home.presentation.combo.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.lospibes.core.component.StandardBoxContainer
import com.example.lospibes.core.component.StandardTopBar
import com.example.lospibes.features.home.component.ComboListRow
import com.example.lospibes.features.home.data.dto.query.GetCombosQueryDto
import com.example.lospibes.features.home.domain.model.Combo
import com.example.lospibes.features.home.domain.model.ComboProduct
import com.example.lospibes.features.home.presentation.combo.component.ComboProductList

@Composable
fun ComboScreen(
    comboViewModel: ComboViewModel = hiltViewModel(),
    onNavigateToHome: () -> Unit,
    onNavigateToDetails: (isCombo: Boolean, id: String) -> Unit
) {
    val comboState = comboViewModel.state.collectAsState()

    LaunchedEffect(key1 = comboState.value.combo) {
        comboState.value.combo?.let { validCombo ->
            comboViewModel.getSimilarCombos(
                getCombosQueryDto = GetCombosQueryDto(
                    name = validCombo.name
                )
            )
        }
    }

    StandardBoxContainer(
        isLoading = comboState.value.isComboLoading &&
                comboState.value.isSimilarComboLoading,
        message = comboState.value.message
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        ) {
            Header(
                isFavorite = false,
                onNavigateToHome = onNavigateToHome
            )

            Body(
                comboState = comboState,
                onNavigateToDetails = onNavigateToDetails
            )

            FooterSection()
        }
    }
}

@Composable
private fun Header(
    isFavorite: Boolean,
    onNavigateToHome: () -> Unit
) {
    val favoriteIcon = if (isFavorite)
        Icons.Filled.Favorite else
        Icons.Filled.FavoriteBorder

    StandardTopBar(
        navigationIcon = {
            Icon(
                imageVector = Icons.Outlined.ArrowBack,
                contentDescription = "Back Icon"
            )
        },
        actions = {
            IconButton(
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = MaterialTheme.colorScheme.primary
                ),
                onClick = { /* TODO */ }
            ) {
                Icon(
                    imageVector = favoriteIcon,
                    contentDescription = "Favorite Icon"
                )
            }
        },
        onBackTo = onNavigateToHome
    )
}

@Composable
private fun Body(
    comboState: State<ComboState>,
    onNavigateToDetails: (isCombo: Boolean, id: String) -> Unit
) {
    val combo: Combo? = comboState.value.combo
    val similarComboList: List<Combo> = comboState.value.similarComboList

    combo?.let { validCombo ->
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            ImageSection(
                combo = validCombo
            )

            Spacer(modifier = Modifier.height(10.dp))

            InfoSection(
                combo = validCombo
            )

            if (validCombo.products.isNotEmpty()) {
                Spacer(modifier = Modifier.height(26.dp))

                ComboProductSection(
                    comboProductList = validCombo.products
                )
            }

            if (similarComboList.isNotEmpty()) {
                Spacer(modifier = Modifier.height(26.dp))

                SimilarSection(
                    similarComboList = similarComboList,
                    onNavigateToDetails = onNavigateToDetails
                )
            }

            Spacer(modifier = Modifier.height(26.dp))
        }
    }
}

@Composable
private fun ImageSection(
    combo: Combo
) {
    AsyncImage(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        model = combo.imageUrl,
        contentDescription = combo.name,
        contentScale = ContentScale.Fit
    )
}

@Composable
private fun InfoSection(
    combo: Combo
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        /* Title with price */
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = combo.name,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$",
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = "${combo.price}",
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }

        Spacer(modifier = Modifier.height(26.dp))

        /* Description */
        combo.description?.let {
            Text(
                modifier = Modifier.alpha(0.8f),
                text = it,
                fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.bodyLarge,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Justify,
                color = MaterialTheme.colorScheme.outline,
                maxLines = 6
            )
        }
    }
}

@Composable
private fun ComboProductSection(
    comboProductList: List<ComboProduct>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = "Productos",
            style = MaterialTheme.typography.titleMedium,
        )

        Spacer(modifier = Modifier.height(22.dp))

        ComboProductList(
            comboProductList = comboProductList
        )
    }
}

@Composable
private fun SimilarSection(
    similarComboList: List<Combo>,
    onNavigateToDetails: (isCombo: Boolean, id: String) -> Unit
) {
    Text(
        modifier = Modifier.padding(horizontal = 20.dp),
        text = "Similares \uD83D\uDCA3",
        style = MaterialTheme.typography.titleMedium,
    )

    Spacer(modifier = Modifier.height(22.dp))

    ComboListRow(
        combos = similarComboList,
        onComboSelected = { selectedCombo ->
            onNavigateToDetails(true, selectedCombo.id)
        }
    )
}

@Composable
private fun FooterSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.extraLarge,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.background
            ),
            onClick = { /*TODO*/ }
        ) {
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = "Agregar",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}