package com.example.lospibes.core.view_model.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lospibes.core.domain.use_case.auth_preference.AuthPreferenceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authPreferenceUseCase: AuthPreferenceUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(AuthState())
    val state = _state.asStateFlow()

    init {
        onEvent(AuthEvent.GetAuthState)
    }

    fun onEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.GetAuthState -> {
                viewModelScope.launch {
                    authPreferenceUseCase.getAuthPreferenceUseCase()
                        .onStart {
                            _state.update {
                                it.copy(
                                    isLoading = true
                                )
                            }
                        }
                        .catch {
                            _state.update {
                                it.copy(
                                    isLoading = false
                                )
                            }
                        }
                        .collect { auth ->
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    accessToken = auth.accessToken,
                                    refreshToken = auth.refreshToken,
                                    userId = auth.userId
                                )
                            }
                        }
                }
            }

            is AuthEvent.SetAuthState -> {
                viewModelScope.launch {
                    authPreferenceUseCase.setAuthPreference(
                        auth = event.value
                    )
                }
            }
        }
    }
}