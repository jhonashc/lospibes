package com.example.lospibes.core.view_model.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lospibes.core.domain.use_case.preference.auth.AuthManagerUseCase
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
    private val authManagerUseCase: AuthManagerUseCase
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
                    authManagerUseCase.getAuthManager()
                        .onStart {
                            _state.update {
                                it.copy(
                                    isLoading = true,
                                    isAuthenticated = false
                                )
                            }
                        }
                        .catch {
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    isAuthenticated = false
                                )
                            }
                        }
                        .collect { auth ->
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    isAuthenticated = auth.userId.isNotEmpty(),
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
                    _state.update {
                        it.copy(
                            isAuthenticated = event.value.userId.isNotEmpty()
                        )
                    }

                    authManagerUseCase.setAuthManager(
                        auth = event.value
                    )
                }
            }

            is AuthEvent.DeleteAuthState -> {
                viewModelScope.launch {
                    _state.update {
                        it.copy(
                            isAuthenticated = false
                        )
                    }

                    authManagerUseCase.deleteAuthManager()
                }
            }
        }
    }
}