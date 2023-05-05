package com.example.lospibes.features.auth.presentation.register.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lospibes.features.auth.data.dto.body.CreateRegisterDto
import com.example.lospibes.features.auth.domain.use_case.AuthUseCase
import com.example.lospibes.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.EnteredUsername -> {
                _state.update {
                    it.copy(
                        username = event.value
                    )
                }
            }

            is RegisterEvent.EnteredEmail -> {
                _state.update {
                    it.copy(
                        email = event.value
                    )
                }
            }

            is RegisterEvent.EnteredTelephone -> {
                _state.update {
                    it.copy(
                        telephone = event.value
                    )
                }
            }

            is RegisterEvent.EnteredPassword -> {
                _state.update {
                    it.copy(
                        password = event.value
                    )
                }
            }

            is RegisterEvent.OnRegisterClick -> {
                register()
            }
        }
    }

    private fun register() {
        viewModelScope.launch {
            authUseCase.register(
                createRegisterDto = CreateRegisterDto(
                    username = state.value.username,
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
                                userId = res.data?.user?.id,
                                token = res.data?.token,
                                expiresIn = res.data?.expiresIn,
                                isLoading = false
                            )
                        }
                    }

                    is NetworkResult.Error -> {
                        _state.update {
                            it.copy(
                                status = false,
                                message = res.message,
                                isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }
}