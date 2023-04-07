package com.example.lospibes.modules.home.detail.presentation

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import com.example.lospibes.common.components.CategoryTabList
import com.example.lospibes.common.components.HorizontalProductList
import com.example.lospibes.utils.Constants.hamburgers
import com.example.lospibes.utils.Constants.products

@Composable
fun DetailScreen(
    productId: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column {
            Content()
        }
    }
}

@Composable
fun Content() {
    Header()
    Body()
}

@Composable
fun Header() {
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
fun Body() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 20.dp),
            text = products[0].name,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(6.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "$${products[0].price}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { /*TODO*/ },
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "Subtract",
                        tint = MaterialTheme.colorScheme.background
                    )
                }

                Text(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    text = "1",
                    style = MaterialTheme.typography.titleMedium
                )

                Button(
                    onClick = { /*TODO*/ },
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowRight,
                        contentDescription = "Subtract",
                        tint = MaterialTheme.colorScheme.background
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(26.dp))

        Text(
            modifier = Modifier.padding(horizontal = 20.dp),
            text = "Categorías \uD83C\uDFF7️",
            style = MaterialTheme.typography.titleMedium,
        )

        Spacer(modifier = Modifier.height(6.dp))

        CategoryTabList(
            categories = products[0].categories,
            selectedCategory = products[0].categories[0],
            onCategorySelected = {}
        )

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            modifier = Modifier
                .alpha(0.6f)
                .padding(horizontal = 20.dp),
            text = products[0].description ?: "empty",
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

        HorizontalProductList(products = hamburgers)

        Spacer(modifier = Modifier.height(26.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Button(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
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
                    .clip(CircleShape),
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