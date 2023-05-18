package com.example.lospibes.features.auth.presentation.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lospibes.features.auth.data.dto.body.CreateLoginDto
import com.example.lospibes.features.auth.domain.use_case.AuthUseCase
import com.example.lospibes.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EnteredEmail -> {
                _state.update {
                    it.copy(
                        email = event.value
                    )
                }
            }

            is LoginEvent.EnteredPassword -> {
                _state.update {
                    it.copy(
                        password = event.value
                    )
                }
            }

            is LoginEvent.OnLoginClick -> {
                login()
            }
        }
    }

    private fun login() {
        viewModelScope.launch {
            authUseCase.login(
                createLoginDto = CreateLoginDto(
                    email = state.value.email,
                    password = state.value.password
                )
            ).collect { res ->
                when (res) {
                    is NetworkResult.Loading -> {
                        _state.update {
                            it.copy(
                                status = false,
                                isLoading = true
                            )
                        }
                    }

                    is NetworkResult.Success -> {
                        _state.update {
                            it.copy(
                                status = true,
                                isLoading = false,
                                userId = res.data?.userId,
                                accessToken = res.data?.accessToken,
                                refreshToken = res.data?.refreshToken
                            )
                        }
                    }

                    is NetworkResult.Error -> {
                        _state.update {
                            it.copy(
                                status = false,
                                isLoading = false,
                                isActive = !res.message.orEmpty().contains("verificado"),
                                message = res.message
                            )
                        }
                    }
                }
            }
        }
    }
}