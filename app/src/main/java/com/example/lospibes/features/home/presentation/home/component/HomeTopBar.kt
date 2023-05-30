package com.example.lospibes.features.home.presentation.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.lospibes.core.component.StandardBottomSheet
import com.example.lospibes.core.component.StandardCenterTopBar

@Composable
fun HomeTopBar(
    onOpenBottomSheet: () -> Unit
) {
    StandardCenterTopBar(
        title = {
            HomeTopBarTitle(
                onOpenBottomSheet = onOpenBottomSheet
            )
        }
    )
}

@Composable
private fun HomeTopBarTitle(
    onOpenBottomSheet: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextButton(
            colors = ButtonDefaults.textButtonColors(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground
            ),
            onClick = onOpenBottomSheet
        ) {
            Text(
                text = "Av. 9 de Octubre",
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.width(5.dp))

            Icon(
                imageVector = Icons.Filled.KeyboardArrowDown,
                contentDescription = "KeyboardArrowDown description"
            )
        }
    }
}

@Composable
fun HomeBottomSheet(
    addressList: List<String>,
    onDismissRequest: () -> Unit
) {
    StandardBottomSheet(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        onDismissRequest = onDismissRequest
    ) {
        HeaderBottomSheet()

        Spacer(modifier = Modifier.height(16.dp))

        BodyBottomSheet(
            addressList = addressList
        )
    }
}

@Composable
private fun HeaderBottomSheet() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "¿Dónde te encuentras?",
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun BodyBottomSheet(
    addressList: List<String>
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        AddressItem(
            address = "Nueva dirección",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = "Add Icon"
                )
            }
        )

        if (addressList.isNotEmpty()) {
            Divider()
        }

        AddressList(
            addressList = addressList,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = "LocationOn Icon"
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Done,
                    contentDescription = "Done Icon"
                )
            }
        )
    }
}

@Composable
fun AddressList(
    addressList: List<String>,
    leadingIcon: @Composable () -> Unit,
    trailingIcon: @Composable () -> Unit = {}
) {
    addressList.forEachIndexed { index, address ->
        AddressItem(
            address = address,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon
        )

        if (addressList.lastIndex != index) {
            Divider()
        }
    }
}

@Composable
fun AddressItem(
    address: String,
    leadingIcon: @Composable () -> Unit,
    trailingIcon: @Composable () -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        leadingIcon()

        Text(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 20.dp),
            text = address,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        trailingIcon()
    }
}