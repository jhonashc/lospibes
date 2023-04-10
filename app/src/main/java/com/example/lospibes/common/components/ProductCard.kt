package com.example.lospibes.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
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
import com.example.lospibes.common.domain.model.Product

@Composable
fun ProductCard(
    product: Product,
    isFavorite: Boolean = false,
    onClick: () -> Unit
) {
    val favoriteIcon = if (isFavorite)
        Icons.Filled.Favorite else
        Icons.Filled.FavoriteBorder

    Card(
        modifier = Modifier
            .width(200.dp)
            .wrapContentHeight()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                modifier = Modifier.size(140.dp),
                model = product.imageUrl,
                contentDescription = product.name,
                contentScale = ContentScale.Fit,
            )

            Icon(
                modifier = Modifier
                    .padding(15.dp)
                    .size(25.dp)
                    .align(Alignment.TopEnd),
                imageVector = favoriteIcon,
                contentDescription = "Favorite",
                tint = MaterialTheme.colorScheme.primary
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = product.name,
                style = MaterialTheme.typography.titleMedium,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.primary,
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                modifier = Modifier.alpha(0.8f),
                text = product.description ?: "",
                fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.bodySmall,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.outline,
                maxLines = 2,
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$${product.price}",
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                ) {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Add",
                            tint = MaterialTheme.colorScheme.background
                        )
                    }
                }
            }
        }
    }
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