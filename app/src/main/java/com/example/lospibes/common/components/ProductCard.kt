package com.example.lospibes.common.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.lospibes.common.domain.model.CardItem
import com.example.lospibes.common.domain.model.Product

@Composable
fun ProductCard(
    product: Product,
    isFavorite: Boolean = false,
    onClick: () -> Unit
) {
    StandardCard(
        cardItem = CardItem(
            name = product.name,
            description = product.description,
            imageUrl = product.imageUrl,
            price = product.price
        ),
        isFavorite = isFavorite,
        onClick = onClick
    )
}

@Composable
fun DetailedProductCard(
    product: Product,
    isFavorite: Boolean = false,
    onClick: () -> Unit
) {
    val favoriteIcon = if (isFavorite)
        Icons.Filled.Favorite else
        Icons.Filled.FavoriteBorder

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .width(100.dp)
                    .height(120.dp),
                model = product.imageUrl,
                contentDescription = product.name,
                contentScale = ContentScale.Fit,
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 15.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 5.dp),
                        text = product.name,
                        style = MaterialTheme.typography.titleMedium,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.primary,
                        maxLines = 1
                    )

                    Icon(
                        modifier = Modifier.size(25.dp),
                        imageVector = favoriteIcon,
                        contentDescription = "Favorite",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    modifier = Modifier.alpha(0.8f),
                    text = product.description ?: "",
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.bodySmall,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.outline,
                    maxLines = 2,
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "$${product.price}",
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}