package com.example.lospibes.features.home.presentation.combo.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.lospibes.features.home.domain.model.ComboProduct
import com.example.lospibes.features.home.domain.model.Product
import com.example.lospibes.utils.Constants
import java.util.Date

@Composable
fun ComboProductItem(
    comboProduct: ComboProduct
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
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
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            AsyncImage(
                modifier = Modifier.size(100.dp),
                model = comboProduct.product.imageUrl,
                contentDescription = comboProduct.product.name,
                contentScale = ContentScale.Fit,
            )

            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Text(
                        text = comboProduct.product.name,
                        style = MaterialTheme.typography.titleMedium,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )

                    Text(
                        modifier = Modifier.alpha(0.8f),
                        text = comboProduct.product.description ?: "",
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.bodySmall,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Justify,
                        color = MaterialTheme.colorScheme.outline,
                        maxLines = 2
                    )
                }

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.3f),
                    text = "${comboProduct.quantity}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                    maxLines = 1
                )
            }
        }
    }
}

@Preview
@Composable
fun ComboItemPreview() {
    ComboProductItem(
        comboProduct = ComboProduct(
            product = Product(
                id = "3",
                name = "Bacon burger Bacon burger Bacon burger Bacon burger Bacon burger",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis faucibus imperdiet odio, in semper risus faucibus ut. Praesent lacinia augue quis lacus posuere dictum. Morbi placerat libero felis, id gravida velit cursus ut. Quisque tempus bibendum urna, non maximus justo.",
                imageUrl = "https://burgerkingec.com/wp-content/uploads/2020/04/bbq-bacon-king-img.png",
                price = 3.50,
                stock = 100,
                categories = Constants.categories,
                createdAt = Date(),
                updatedAt = Date()
            ),
            quantity = 2
        ),
    )
}