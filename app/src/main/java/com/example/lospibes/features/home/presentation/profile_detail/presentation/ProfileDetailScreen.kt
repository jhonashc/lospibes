package com.example.lospibes.features.home.presentation.profile_detail.presentation

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.lospibes.R
import com.example.lospibes.core.component.StandardScaffold
import com.example.lospibes.core.view_model.auth.AuthViewModel
import com.example.lospibes.features.home.presentation.profile_detail.component.FirstnameTextField
import com.example.lospibes.features.home.presentation.profile_detail.component.LastnameTextField
import com.example.lospibes.features.home.presentation.profile_detail.component.ProfileDetailTopBar
import com.example.lospibes.features.home.presentation.profile_detail.component.TelephoneTextField
import com.example.lospibes.features.home.presentation.profile_detail.component.UsernameTextField

@Composable
fun ProfileDetailScreen(
    authViewModel: AuthViewModel,
    profileDetailViewModel: ProfileDetailViewModel = hiltViewModel(),
    onNavigateToProfile: () -> Unit
) {
    val authState = authViewModel.state.collectAsState()
    val profileDetailState = profileDetailViewModel.state.collectAsState()

    LaunchedEffect(key1 = Unit) {
        profileDetailViewModel.getUserById(
            userId = authState.value.userId
        )
    }

    StandardScaffold(
        topAppBar = {
            Header(
                onNavigateToProfile = onNavigateToProfile
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            if (profileDetailState.value.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            if (!profileDetailState.value.isLoading) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.TopStart),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Body(
                        profileDetailViewModel = profileDetailViewModel
                    )

                    Footer(
                        profileDetailViewModel = profileDetailViewModel
                    )
                }
            }
        }
    }
}

@Composable
private fun Header(
    onNavigateToProfile: () -> Unit
) {
    ProfileDetailTopBar(
        onNavigateToProfile = onNavigateToProfile
    )
}

@Composable
private fun Body(
    profileDetailViewModel: ProfileDetailViewModel
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        ProfileImageSection(
            profileDetailViewModel = profileDetailViewModel
        )

        Spacer(modifier = Modifier.height(26.dp))

        DetailSection(
            profileDetailViewModel = profileDetailViewModel
        )
    }
}

@Composable
private fun ProfileImageSection(
    profileDetailViewModel: ProfileDetailViewModel
) {
    val profileDetailState = profileDetailViewModel.state.collectAsState()

    val profileImage = if (profileDetailState.value.selectedImageUri == null) {
        profileDetailState.value.user?.avatarUrl
    } else {
        profileDetailState.value.selectedImageUri
    }

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { selectedImageUri ->
            if (selectedImageUri != null) {
                profileDetailViewModel.onEvent(
                    ProfileDetailEvent.EnteredSelectedImageUri(
                        selectedImageUri
                    )
                )
            }
        }
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.size(160.dp),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape),
                model = profileImage,
                contentDescription = profileDetailState.value.user?.username,
                contentScale = ContentScale.Crop,
            )

            OutlinedIconButton(
                modifier = Modifier.align(Alignment.BottomEnd),
                colors = IconButtonDefaults.outlinedIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.background
                ),
                border = BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary
                ),
                onClick = {
                    singlePhotoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }
            ) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.baseline_camera_alt_24
                    ),
                    contentDescription = "Camera Icon"
                )
            }
        }
    }
}

@Composable
fun DetailSection(
    profileDetailViewModel: ProfileDetailViewModel
) {
    val profileDetailState = profileDetailViewModel.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        UsernameTextField(
            value = profileDetailState.value.username,
            onValueChange = {
                profileDetailViewModel.onEvent(ProfileDetailEvent.EnteredUsername(it))
            }
        )

        FirstnameTextField(
            value = profileDetailState.value.firstName,
            onValueChange = {
                profileDetailViewModel.onEvent(ProfileDetailEvent.EnteredFirstname(it))
            }
        )

        LastnameTextField(
            value = profileDetailState.value.lastName,
            onValueChange = {
                profileDetailViewModel.onEvent(ProfileDetailEvent.EnteredLastname(it))
            }
        )

        TelephoneTextField(
            value = profileDetailState.value.telephone,
            onValueChange = {
                profileDetailViewModel.onEvent(ProfileDetailEvent.EnteredTelephone(it))
            }
        )
    }
}

@Composable
private fun Footer(
    profileDetailViewModel: ProfileDetailViewModel
) {
    val profileDetailState = profileDetailViewModel.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            enabled = !profileDetailState.value.isLoading,
            shape = MaterialTheme.shapes.extraLarge,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.background
            ),
            onClick = { /*TODO*/ }
        ) {
            if (profileDetailState.value.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(25.dp),
                    color = MaterialTheme.colorScheme.background
                )
            } else {
                Text(
                    text = "Guardar",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}