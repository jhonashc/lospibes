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
        getToken()
        getUserId()
    }

    fun onEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.SetToken -> {
                viewModelScope.launch {
                    authPreferenceRepository.setToken(event.value)
                }
            }

            is AuthEvent.SetUserId -> {
                viewModelScope.launch {
                    authPreferenceRepository.setUserId(event.value)
                }
            }
        }
    }

    private fun getToken() {
        viewModelScope.launch {
            authPreferenceRepository.getToken().collect { token ->
                _state.update {
                    it.copy(
                        token = token
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