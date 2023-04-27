package com.example.lospibes.features.home.presentation.cart.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.lospibes.features.home.domain.model.CartItem
import com.example.lospibes.utils.Constants.products
import com.example.lospibes.R

@Composable
fun CartListItem(
    cartItem: CartItem
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
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
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                /* Info section */
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "${cartItem.name} ${cartItem.name} ${cartItem.name}",
                        style = MaterialTheme.typography.titleMedium,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "$",
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary,
                            maxLines = 1
                        )

                        Text(
                            text = "${cartItem.price}",
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.titleMedium,
                            maxLines = 1
                        )
                    }
                }

                /* Button section */
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedIconButton(
                        shape = MaterialTheme.shapes.extraLarge,
                        colors = IconButtonDefaults.outlinedIconButtonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.background
                        ),
                        border = BorderStroke(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            painter = painterResource(
                                id = R.drawable.baseline_remove_24
                            ),
                            contentDescription = "Subtract Icon"
                        )
                    }

                    Text(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        text = "1",
                        style = MaterialTheme.typography.titleMedium
                    )

                    OutlinedIconButton(
                        shape = MaterialTheme.shapes.extraLarge,
                        colors = IconButtonDefaults.outlinedIconButtonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.background
                        ),
                        border = BorderStroke(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            painter = painterResource(
                                id = R.drawable.baseline_add_24
                            ),
                            contentDescription = "Add Icon"
                        )
                    }
                }
            }

            /* Body section */
//            Column(
//                modifier = Modifier.fillMaxSize(),
//                verticalArrangement = Arrangement.Center
//            ) {
//                /* Info section */
//                Text(
//                    text = "${cartItem.name} ${cartItem.name} ${cartItem.name}",
//                    style = MaterialTheme.typography.titleMedium,
//                    overflow = TextOverflow.Ellipsis,
//                    maxLines = 2
//                )
//
//                Spacer(modifier = Modifier.height(8.dp))
//
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.spacedBy(4.dp)
//                ) {
//                    Text(
//                        text = "$",
//                        fontWeight = FontWeight.SemiBold,
//                        style = MaterialTheme.typography.titleMedium,
//                        color = MaterialTheme.colorScheme.primary,
//                        maxLines = 1
//                    )
//
//                    Text(
//                        text = "${cartItem.price}",
//                        fontWeight = FontWeight.SemiBold,
//                        style = MaterialTheme.typography.titleMedium,
//                        maxLines = 1
//                    )
//                }
//
//                Spacer(modifier = Modifier.height(10.dp))
//
//                /* Button section */
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    OutlinedIconButton(
//                        shape = MaterialTheme.shapes.extraSmall,
//                        colors = IconButtonDefaults.outlinedIconButtonColors(
//                            containerColor = MaterialTheme.colorScheme.primary,
//                            contentColor = MaterialTheme.colorScheme.background
//                        ),
//                        border = BorderStroke(
//                            width = 1.dp,
//                            color = MaterialTheme.colorScheme.background
//                        ),
//                        onClick = { /*TODO*/ }
//                    ) {
//                        Icon(
//                            modifier = Modifier.size(18.dp),
//                            painter = painterResource(
//                                id = R.drawable.baseline_remove_24
//                            ),
//                            contentDescription = "Subtract Icon"
//                        )
//                    }
//
//                    Text(
//                        modifier = Modifier.padding(horizontal = 20.dp),
//                        text = "1",
//                        style = MaterialTheme.typography.titleMedium
//                    )
//
//                    OutlinedIconButton(
//                        shape = MaterialTheme.shapes.extraSmall,
//                        colors = IconButtonDefaults.outlinedIconButtonColors(
//                            containerColor = MaterialTheme.colorScheme.primary,
//                            contentColor = MaterialTheme.colorScheme.background
//                        ),
//                        border = BorderStroke(
//                            width = 1.dp,
//                            color = MaterialTheme.colorScheme.background
//                        ),
//                        onClick = { /*TODO*/ }
//                    ) {
//                        Icon(
//                            modifier = Modifier.size(18.dp),
//                            painter = painterResource(
//                                id = R.drawable.baseline_add_24
//                            ),
//                            contentDescription = "Add Icon"
//                        )
//                    }
//                }
//            }
        }
    }
}

@Preview
@Composable
fun CartListItemPreview() {
    CartListItem(
        cartItem = CartItem(
            id = "1",
            name = "Funchose with shirmps",
            imageUrl = products[0].imageUrl,
            price = products[0].price,
            quantity = 1
        )
    )
}