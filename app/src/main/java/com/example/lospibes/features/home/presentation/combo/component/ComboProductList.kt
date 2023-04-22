package com.example.lospibes.features.home.presentation.combo.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lospibes.features.home.domain.model.ComboProduct

@Composable
fun ComboProductList(
    comboProductList: List<ComboProduct>
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        comboProductList.forEach { comboProduct ->
            ComboProductItem(
                comboProduct = comboProduct
            )
        }
    }
}