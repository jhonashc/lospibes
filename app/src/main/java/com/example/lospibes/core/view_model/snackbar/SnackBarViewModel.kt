package com.example.lospibes.core.view_model.snackbar

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SnackBarViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(SnackBarState())
    val state = _state.asStateFlow()

    fun onEvent(event: SnackBarEvent) {
        when (event) {
            is SnackBarEvent.SetMessage -> {
                _state.update {
                    it.copy(
                        message = event.value
                    )
                }
            }
        }
    }
}