package com.example.lospibes.features.home.presentation.cart.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.lospibes.features.home.domain.model.CartItem
import com.example.lospibes.R
import com.example.lospibes.utils.capitalizeText
import com.example.lospibes.utils.formatNumber

@Composable
fun CartListItem(
    cartItem: CartItem,
    onAddQuantityClick: () -> Unit,
    onSubtractQuantityClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp),
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
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            /* Image section */
            AsyncImage(
                modifier = Modifier.size(70.dp),
                model = cartItem.imageUrl,
                contentDescription = cartItem.name,
                contentScale = ContentScale.Fit,
            )

            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                /* Info section */
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = capitalizeText(cartItem.name),
                        style = MaterialTheme.typography.titleMedium,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2
                    )

                    Text(
                        text = "$ ${formatNumber(cartItem.price)}",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleSmall,
                        maxLines = 1
                    )
                }

                /* Button section */
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedIconButton(
                        modifier = Modifier.size(40.dp),
                        shape = MaterialTheme.shapes.extraLarge,
                        colors = IconButtonDefaults.outlinedIconButtonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.background
                        ),
                        border = BorderStroke(
                            width = 1.dp,
                            color = Color.Transparent
                        ),
                        onClick = onSubtractQuantityClick
                    ) {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.baseline_remove_24
                            ),
                            contentDescription = "Subtract Icon"
                        )
                    }

                    Text(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        text = "${cartItem.quantity}",
                        style = MaterialTheme.typography.titleMedium
                    )

                    OutlinedIconButton(
                        modifier = Modifier.size(40.dp),
                        shape = MaterialTheme.shapes.extraLarge,
                        colors = IconButtonDefaults.outlinedIconButtonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.background
                        ),
                        border = BorderStroke(
                            width = 1.dp,
                            color = Color.Transparent
                        ),
                        onClick = onAddQuantityClick
                    ) {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.baseline_add_24
                            ),
                            contentDescription = "Add Icon"
                        )
                    }
                }
            }
        }
    }
}