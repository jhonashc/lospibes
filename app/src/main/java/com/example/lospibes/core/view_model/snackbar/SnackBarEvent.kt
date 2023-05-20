package com.example.lospibes.core.view_model.snackbar

sealed class SnackBarEvent {
    data class SetMessage(val value: String) : SnackBarEvent()
}