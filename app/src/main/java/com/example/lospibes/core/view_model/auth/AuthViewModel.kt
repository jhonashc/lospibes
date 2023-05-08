package com.example.lospibes.core.view_model.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lospibes.core.domain.repository.AuthPreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authPreferenceRepository: AuthPreferenceRepository
) : ViewModel() {
    private val _state = MutableStateFlow(AuthState())
    val state = _state.asStateFlow()

    init {
        getAccessToken()
        getRefreshToken()
        getUserId()
    }

    fun onEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.SetAccessToken -> {
                viewModelScope.launch {
                    authPreferenceRepository.setAccessToken(event.value)
                }
            }

            is AuthEvent.SetRefreshToken -> {
                viewModelScope.launch {
                    authPreferenceRepository.setRefreshToken(event.value)
                }
            }

            is AuthEvent.SetUserId -> {
                viewModelScope.launch {
                    authPreferenceRepository.setUserId(event.value)
                }
            }
        }
    }

    private fun getAccessToken() {
        viewModelScope.launch {
            authPreferenceRepository.getAccessToken().collect { accessToken ->
                _state.update {
                    it.copy(
                        accessToken = accessToken
                    )
                }
            }
        }
    }

    private fun getRefreshToken() {
        viewModelScope.launch {
            authPreferenceRepository.getRefreshToken().collect { refreshToken ->
                _state.update {
                    it.copy(
                        refreshToken = refreshToken
                    )
                }
            }
        }
    }

    private fun getUserId() {
        viewModelScope.launch {
            authPreferenceRepository.getUserId().collect { userId ->
                _state.update {
                    it.copy(
                        userId = userId
                    )
                }
            }
        }
    }
}