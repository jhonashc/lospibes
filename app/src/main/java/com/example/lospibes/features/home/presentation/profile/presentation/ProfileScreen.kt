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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.lospibes.core.component.StandardTopBar
import com.example.lospibes.features.home.domain.model.SectionItem
import com.example.lospibes.features.home.presentation.profile.component.SectionList
import com.example.lospibes.R
import com.example.lospibes.core.domain.model.Auth
import com.example.lospibes.core.domain.model.User
import com.example.lospibes.core.view_model.auth.AuthEvent
import com.example.lospibes.core.view_model.auth.AuthViewModel
import com.example.lospibes.utils.capitalizeText

@Composable
fun ProfileScreen(
    authViewModel: AuthViewModel,
    profileViewModel: ProfileViewModel = hiltViewModel(),
    onNavigateToHome: () -> Unit
) {
    val authState = authViewModel.state.collectAsState()
    val profileState = profileViewModel.state.collectAsState()

    LaunchedEffect(key1 = Unit) {
        profileViewModel.getUserById(
            userId = authState.value.userId
        )
    }

    if (!profileState.value.isLoading && profileState.value.status) {
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

                Body(
                    profileViewModel = profileViewModel
                )
            }

            Footer(
                authViewModel = authViewModel
            )
        }
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
private fun Body(
    profileViewModel: ProfileViewModel
) {
    val profileState = profileViewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        DetailSection(
            user = profileState.value.user!!
        )

        Spacer(modifier = Modifier.height(26.dp))

        SettingSection()

        Spacer(modifier = Modifier.height(26.dp))
    }
}

@Composable
fun DetailSection(
    user: User
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier
                .size(140.dp)
                .clip(CircleShape),
            model = user.avatarUrl,
            contentDescription = user.username,
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = capitalizeText(user.username),
            style = MaterialTheme.typography.titleLarge,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            maxLines = 1
        )

        Text(
            text = user.email,
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
            icon = painterResource(
                id = R.drawable.baseline_article_24
            )
        ),
        SectionItem(
            name = "Métodos de pago",
            description = "Efectivo",
            route = "",
            icon = painterResource(
                id = R.drawable.baseline_payment_24
            )
        ),
        SectionItem(
            name = "Direcciones de envío",
            description = "2009, Robert Browning St, Mosaic at Monastery",
            route = "",
            icon = painterResource(
                id = R.drawable.baseline_location_on_24
            )
        ),
        SectionItem(
            name = "Notificaciones",
            description = "Order #3 Pending",
            route = "",
            icon = painterResource(
                id = R.drawable.baseline_notifications_24
            )
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
private fun Footer(
    authViewModel: AuthViewModel
) {
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
            onClick = {
                authViewModel.onEvent(
                    AuthEvent.SetAuthState(
                        Auth(
                            accessToken = "",
                            refreshToken = "",
                            userId = ""
                        )
                    )
                )
            }
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