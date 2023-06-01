package com.example.lospibes.features.home.presentation.profile_detail.presentation

import android.net.Uri

sealed class ProfileDetailEvent {
    data class EnteredUsername(val value: String) : ProfileDetailEvent()
    data class EnteredFirstname(val value: String) : ProfileDetailEvent()
    data class EnteredLastname(val value: String) : ProfileDetailEvent()
    data class EnteredTelephone(val value: String) : ProfileDetailEvent()
    data class EnteredSelectedImageUri(val value: Uri) : ProfileDetailEvent()
}
