package com.example.lospibes.features.home.details.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.lospibes.common.components.ComboList
import com.example.lospibes.common.components.StandardScaffold
import com.example.lospibes.common.components.StandardTopAppBar
import com.example.lospibes.utils.Constants.combos

@Composable
fun ComboDetailsScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToComboDetails: (comboId: String) -> Unit
) {
    StandardScaffold(
        topAppBar = {
            StandardTopAppBar(
                title = "Detalles",
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateToHome
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Column {
                Content(
                    onNavigateToComboDetails = onNavigateToComboDetails
                )
            }
        }
    }
}

@Composable
private fun Content(
    onNavigateToComboDetails: (comboId: String) -> Unit
) {
    Header()
    Body(
        onNavigateToComboDetails = onNavigateToComboDetails
    )
}

@Composable
private fun Header() {
    AsyncImage(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        model = combos[1].imageUrl,
        contentDescription = combos[1].name,
        contentScale = ContentScale.Fit
    )
}

@Composable
private fun Body(
    onNavigateToComboDetails: (comboId: String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 20.dp),
            text = combos[1].name,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(15.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "$${combos[1].price}",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .clip(MaterialTheme.shapes.extraLarge)
                        .background(MaterialTheme.colorScheme.primary)
                ) {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowLeft,
                            contentDescription = "Subtract",
                            tint = MaterialTheme.colorScheme.background
                        )
                    }
                }

                Text(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    text = "1",
                    style = MaterialTheme.typography.titleMedium
                )

                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .clip(MaterialTheme.shapes.extraLarge)
                        .background(MaterialTheme.colorScheme.primary)
                ) {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowRight,
                            contentDescription = "Add",
                            tint = MaterialTheme.colorScheme.background
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(26.dp))

        Text(
            modifier = Modifier
                .alpha(0.8f)
                .padding(horizontal = 20.dp),
            text = combos[1].description ?: "empty",
            fontWeight = FontWeight.Normal,
            style = MaterialTheme.typography.bodyLarge,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Justify,
            color = MaterialTheme.colorScheme.outline,
            maxLines = 6,
        )

        Spacer(modifier = Modifier.height(26.dp))

        Text(
            modifier = Modifier.padding(horizontal = 20.dp),
            text = "Similares \uD83D\uDCA3️",
            style = MaterialTheme.typography.titleMedium,
        )

        Spacer(modifier = Modifier.height(22.dp))

        ComboList(
            combos = combos,
            favoriteCombos = combos.subList(0, 2),
            onNavigateTo = onNavigateToComboDetails
        )

        Spacer(modifier = Modifier.height(46.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Button(
                modifier = Modifier
                    .weight(1f)
                    .clip(MaterialTheme.shapes.extraLarge)
                    .background(color = MaterialTheme.colorScheme.primary),
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    modifier = Modifier
                        .size(30.dp)
                        .padding(8.dp),
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Add",
                    tint = MaterialTheme.colorScheme.background
                )

                Text(
                    text = "Agregar",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            OutlinedButton(
                modifier = Modifier
                    .weight(0.3f)
                    .clip(MaterialTheme.shapes.extraLarge),
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    modifier = Modifier
                        .size(35.dp)
                        .padding(8.dp),
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                )
            }
        }
    }
}