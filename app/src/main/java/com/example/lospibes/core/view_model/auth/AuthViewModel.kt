package com.example.lospibes.core.view_model.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lospibes.core.domain.use_case.auth_preference.AuthPreferenceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authPreferenceUseCase: AuthPreferenceUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(AuthState())
    val state = _state.asStateFlow()

    fun onEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.SetAccessToken -> {
                viewModelScope.launch {
                    authPreferenceUseCase.setAccessToken(event.value)
                }
            }

            is AuthEvent.SetRefreshToken -> {
                viewModelScope.launch {
                    authPreferenceUseCase.setRefreshToken(event.value)
                }
            }

            is AuthEvent.SetUserId -> {
                viewModelScope.launch {
                    authPreferenceUseCase.setUserId(event.value)
                }
            }
        }
    }

    fun getAccessToken() {
        viewModelScope.launch {
            authPreferenceUseCase.getAccessToken().collect { accessToken ->
                _state.update {
                    it.copy(
                        accessToken = accessToken
                    )
                }
            }
        }
    }

    fun getRefreshToken() {
        viewModelScope.launch {
            authPreferenceUseCase.getRefreshToken().collect { refreshToken ->
                _state.update {
                    it.copy(
                        refreshToken = refreshToken
                    )
                }
            }
        }
    }

    fun getUserId() {
        viewModelScope.launch {
            authPreferenceUseCase.getUserId().collect { userId ->
                _state.update {
                    it.copy(
                        userId = userId
                    )
                }
            }
        }
    }
}