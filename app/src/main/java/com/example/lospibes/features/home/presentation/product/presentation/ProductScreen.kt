package com.example.lospibes.features.home.presentation.product.presentation

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.lospibes.core.components.*
import com.example.lospibes.utils.Constants.hamburgers
import com.example.lospibes.utils.Constants.products

@Composable
fun ProductScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToDetails: (isCombo: Boolean, id: String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        ) {
            Header(
                onNavigateToHome = onNavigateToHome
            )

            Body(
                onNavigateToDetails = onNavigateToDetails
            )

            FooterSection()
        }
    }
}

@Composable
private fun Header(
    onNavigateToHome: () -> Unit
) {
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
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Favorite Icon"
                )
            }
        },
        onBackTo = onNavigateToHome
    )
}

@Composable
private fun Body(
    onNavigateToDetails: (isCombo: Boolean, id: String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        ImageSection()

        Spacer(modifier = Modifier.height(10.dp))

        InfoSection()

        Spacer(modifier = Modifier.height(26.dp))

        SimilarSection(
            onNavigateToDetails = onNavigateToDetails
        )

        Spacer(modifier = Modifier.height(26.dp))
    }
}

@Composable
private fun ImageSection() {
    AsyncImage(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        model = products[0].imageUrl,
        contentDescription = products[0].name,
        contentScale = ContentScale.Fit
    )
}

@Composable
private fun InfoSection() {
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
                text = products[0].name,
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
                    text = "${products[0].price}",
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }

        Spacer(modifier = Modifier.height(26.dp))

        /* Description */
        products[0].description?.let {
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
private fun SimilarSection(
    onNavigateToDetails: (isCombo: Boolean, id: String) -> Unit
) {
    Text(
        modifier = Modifier.padding(horizontal = 20.dp),
        text = "Similares \uD83D\uDCA3️",
        style = MaterialTheme.typography.titleMedium,
    )

    Spacer(modifier = Modifier.height(22.dp))

    ProductListRow(
        products = hamburgers,
        favoriteProducts = hamburgers.subList(0, 1),
        onProductSelected = { selectedProduct ->
            onNavigateToDetails(false, selectedProduct.id)
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
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}