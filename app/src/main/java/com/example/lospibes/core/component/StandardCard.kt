package com.example.lospibes.core.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.lospibes.features.home.domain.model.CardItem
import com.example.lospibes.utils.capitalizeText
import com.example.lospibes.utils.formatNumber

@Composable
fun StandardCard(
    cardItem: CardItem,
    isFavorite: Boolean = false,
    isOnTheCart: Boolean = false,
    showFavIcon: Boolean = false,
    onCardClick: () -> Unit,
    onAddOrRemoveClick: (selectedCardItem: CardItem) -> Unit = {}
) {
    val favoriteIcon = if (isFavorite)
        Icons.Filled.Favorite else
        Icons.Filled.FavoriteBorder

    val buttonIcon = if (isOnTheCart)
        Icons.Filled.Delete else
        Icons.Filled.Add

    val containerButtonColor = if (isOnTheCart)
        MaterialTheme.colorScheme.error else
        MaterialTheme.colorScheme.primary

    val favoriteContentButtonColor = if (isFavorite)
        MaterialTheme.colorScheme.error else
        MaterialTheme.colorScheme.onBackground


    Card(
        modifier = Modifier
            .width(180.dp)
            .height(265.dp)
            .clip(MaterialTheme.shapes.small)
            .clickable(onClick = onCardClick),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    modifier = Modifier.size(120.dp),
                    model = cardItem.imageUrl,
                    contentDescription = cardItem.name,
                    contentScale = ContentScale.Fit
                )

                if (showFavIcon) {
                    OutlinedIconButton(
                        modifier = Modifier.align(Alignment.TopEnd),
                        shape = MaterialTheme.shapes.extraLarge,
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = MaterialTheme.colorScheme.outline.copy(0.15f),
                            contentColor = favoriteContentButtonColor
                        ),
                        border = BorderStroke(
                            width = 1.dp,
                            color = Color.Transparent
                        ),
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            imageVector = favoriteIcon,
                            contentDescription = "Favorite Icon"
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = capitalizeText(cardItem.name),
                    style = MaterialTheme.typography.titleMedium,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

                if (cardItem.description != null) {
                    Text(
                        modifier = Modifier.alpha(0.8f),
                        text = cardItem.description,
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.bodySmall,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Justify,
                        color = MaterialTheme.colorScheme.outline,
                        maxLines = 2
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "$ ${formatNumber(cardItem.price)}",
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.titleMedium
                    )

                    OutlinedIconButton(
                        shape = MaterialTheme.shapes.extraSmall,
                        colors = IconButtonDefaults.outlinedIconButtonColors(
                            containerColor = containerButtonColor,
                            contentColor = MaterialTheme.colorScheme.background
                        ),
                        border = BorderStroke(
                            width = 1.dp,
                            color = Color.Transparent
                        ),
                        onClick = {
                            onAddOrRemoveClick(cardItem)
                        }
                    ) {
                        Icon(
                            imageVector = buttonIcon,
                            contentDescription = "Add Icon"
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StandardColumCard(
    cardItem: CardItem,
    isOnTheCart: Boolean = false,
    onCardClick: () -> Unit,
    onAddOrRemoveClick: (selectedCardItem: CardItem) -> Unit = {}
) {
    val buttonIcon = if (isOnTheCart)
        Icons.Filled.Delete else
        Icons.Filled.Add

    val containerButtonColor = if (isOnTheCart)
        MaterialTheme.colorScheme.error else
        MaterialTheme.colorScheme.primary

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .clip(MaterialTheme.shapes.medium)
            .clickable(onClick = onCardClick),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            AsyncImage(
                modifier = Modifier.size(80.dp),
                model = cardItem.imageUrl,
                contentDescription = cardItem.name,
                contentScale = ContentScale.Fit
            )

            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = capitalizeText(cardItem.name),
                        style = MaterialTheme.typography.titleMedium,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )

                    if (cardItem.description != null) {
                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            modifier = Modifier.alpha(0.8f),
                            text = cardItem.description,
                            fontWeight = FontWeight.Normal,
                            style = MaterialTheme.typography.bodySmall,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Justify,
                            color = MaterialTheme.colorScheme.outline,
                            maxLines = 2
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .width(65.dp)
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "$ ${formatNumber(cardItem.price)}",
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    OutlinedIconButton(
                        shape = MaterialTheme.shapes.extraSmall,
                        colors = IconButtonDefaults.outlinedIconButtonColors(
                            containerColor = containerButtonColor,
                            contentColor = MaterialTheme.colorScheme.background
                        ),
                        border = BorderStroke(
                            width = 1.dp,
                            color = Color.Transparent
                        ),
                        onClick = {
                            onAddOrRemoveClick(cardItem)
                        }
                    ) {
                        Icon(
                            imageVector = buttonIcon,
                            contentDescription = "Add Icon"
                        )
                    }
                }
            }
        }
    }
}