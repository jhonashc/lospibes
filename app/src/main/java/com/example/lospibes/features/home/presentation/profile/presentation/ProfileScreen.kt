package com.example.lospibes.features.home.presentation.profile.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import com.example.lospibes.features.home.domain.model.SectionItem
import com.example.lospibes.features.home.presentation.profile.component.SectionList
import com.example.lospibes.R
import com.example.lospibes.core.component.StandardNotAuthenticated
import com.example.lospibes.core.component.StandardScaffold
import com.example.lospibes.core.domain.model.User
import com.example.lospibes.core.view_model.auth.AuthEvent
import com.example.lospibes.core.view_model.auth.AuthViewModel
import com.example.lospibes.features.home.presentation.profile.component.ProfileTopBar
import com.example.lospibes.navigation.home.DetailsDestinations
import com.example.lospibes.utils.capitalizeText

@Composable
fun ProfileScreen(
    authViewModel: AuthViewModel,
    profileViewModel: ProfileViewModel = hiltViewModel(),
    onNavigateToHome: () -> Unit,
    onNavigateToProfileDetails: () -> Unit,
    onNavigateToSection: (route: String) -> Unit
) {
    val authState = authViewModel.state.collectAsState()
    val profileState = profileViewModel.state.collectAsState()

    LaunchedEffect(key1 = Unit) {
        if (authState.value.isAuthenticated) {
            profileViewModel.getUserById(
                userId = authState.value.userId
            )
        }
    }

    if (!authState.value.isAuthenticated) {
        StandardNotAuthenticated()
    } else {
        StandardScaffold(
            topAppBar = {
                Header(
                    onNavigateToHome = onNavigateToHome,
                    onNavigateToProfileDetails = onNavigateToProfileDetails
                )
            }
        ) {
            if (!profileState.value.isLoading) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Body(
                        profileViewModel = profileViewModel,
                        onNavigateToSection = onNavigateToSection
                    )

                    Footer(
                        authViewModel = authViewModel
                    )
                }
            }
        }
    }
}

@Composable
private fun Header(
    onNavigateToHome: () -> Unit,
    onNavigateToProfileDetails: () -> Unit
) {
    ProfileTopBar(
        onNavigateToHome = onNavigateToHome,
        onNavigateToProfileDetails = onNavigateToProfileDetails
    )
}

@Composable
private fun Body(
    profileViewModel: ProfileViewModel,
    onNavigateToSection: (route: String) -> Unit
) {
    val profileState = profileViewModel.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        DetailSection(
            user = profileState.value.user!!
        )

        Spacer(modifier = Modifier.height(26.dp))

        SettingSection(
            onNavigateToSection = onNavigateToSection
        )

        Spacer(modifier = Modifier.height(26.dp))
    }
}

@Composable
fun DetailSection(
    user: User
) {
    val profileImage = if (user.avatarUrl.isNullOrEmpty()) {
        R.drawable.ic_launcher_background
    } else {
        user.avatarUrl
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier
                .size(140.dp)
                .clip(CircleShape),
            model = profileImage,
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

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = user.email,
            style = MaterialTheme.typography.titleSmall,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.outline,
            maxLines = 1
        )
    }
}

@Composable
private fun SettingSection(
    onNavigateToSection: (route: String) -> Unit
) {
    val sectionList: List<SectionItem> = listOf(
        SectionItem(
            name = "Mis direcciones",
            route = DetailsDestinations.AddressesScreen.route,
            icon = painterResource(
                id = R.drawable.baseline_location_on_24
            )
        )
    )

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        SectionList(
            sectionList = sectionList,
            onSectionSelected = { selectedSectionItem ->
                onNavigateToSection(selectedSectionItem.route)
            }
        )
    }
}

@Composable
private fun Footer(
    authViewModel: AuthViewModel
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = MaterialTheme.shapes.extraLarge,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.background
            ),
            onClick = {
                authViewModel.onEvent(AuthEvent.DeleteAuthState)
            }
        ) {
            Text(
                text = "Cerrar sesión",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}