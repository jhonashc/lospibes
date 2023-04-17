package com.example.lospibes.features.home.presentation.profile.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.lospibes.core.component.StandardTopBar
import com.example.lospibes.features.home.domain.model.SectionItem
import com.example.lospibes.features.home.presentation.profile.component.SectionList
import com.example.lospibes.R

@Composable
fun ProfileScreen(
    onNavigateToHome: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Header(
                onNavigateToHome = onNavigateToHome
            )

            Body()
        }

        Footer()
    }
}

@Composable
private fun Header(
    onNavigateToHome: () -> Unit
) {
    StandardTopBar(
        title = "Perfil",
        navigationIcon = {
            Icon(
                imageVector = Icons.Outlined.ArrowBack,
                contentDescription = "Back Icon"
            )
        },
        actions = {
            IconButton(
                modifier = Modifier.padding(end = 5.dp),
                onClick = { /* TODO */ }
            ) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Edit Icon"
                )
            }
        },
        onBackTo = onNavigateToHome
    )
}

@Composable
private fun Body() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        DetailSection()

        Spacer(modifier = Modifier.height(26.dp))

        SettingSection()
    }
}

@Composable
fun DetailSection() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier
                .size(180.dp)
                .clip(CircleShape),
            model = "https://images.pexels.com/photos/3778361/pexels-photo-3778361.jpeg",
            contentDescription = "Profile Image",
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = "SoyMulti",
            style = MaterialTheme.typography.headlineSmall,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            maxLines = 1
        )

        Text(
            text = "jhonhuiracocha@gmail.com",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.outline,
            maxLines = 1
        )
    }
}

@Composable
private fun SettingSection() {
    val sectionList: List<SectionItem> = listOf(
        SectionItem(
            name = "Mis órdenes",
            description = "Información sobre las órdenes",
            route = "",
            icon = painterResource(id = R.drawable.baseline_article_24)
        ),
        SectionItem(
            name = "Métodos de pago",
            description = "Efectivo",
            route = "",
            icon = painterResource(id = R.drawable.baseline_payment_24)
        ),
        SectionItem(
            name = "Direcciones de envío",
            description = "2009, Robert Browning St, Mosaic at Monastery",
            route = "",
            icon = painterResource(id = R.drawable.baseline_location_on_24)
        ),
        SectionItem(
            name = "Notificaciones",
            description = "Order #3 Pending",
            route = "",
            icon = painterResource(id = R.drawable.baseline_notifications_24)
        )
    )

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        SectionList(
            sectionList = sectionList,
            onSectionSelected = {}
        )
    }
}

@Composable
private fun Footer() {
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
                text = "Cerrar sesión",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}