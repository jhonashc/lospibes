package com.example.lospibes.features.auth.presentation.otp.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lospibes.features.auth.data.dto.body.CreateResendEmailVerificationDto
import com.example.lospibes.features.auth.data.dto.body.CreateVerifyAccountDto
import com.example.lospibes.features.auth.domain.use_case.AuthUseCase
import com.example.lospibes.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OtpViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(OtpState())
    val state = _state.asStateFlow()

    val email: String = savedStateHandle["email"] ?: ""

    fun onEvent(event: OtpEvent) {
        when (event) {
            is OtpEvent.EnteredOtp -> {
                _state.update {
                    it.copy(
                        otp = event.value
                    )
                }
            }

            is OtpEvent.OnSubmit -> {
                verifyAccount()
            }

            is OtpEvent.OnResend -> {
                resendEmail()
            }
        }
    }

    private fun verifyAccount() {
        viewModelScope.launch {
            authUseCase.verifyAccount(
                createVerifyAccountDto = CreateVerifyAccountDto(
                    email = email,
                    otp = state.value.otp
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
                                message = res.data?.message
                            )
                        }
                    }

                    is NetworkResult.Error -> {
                        _state.update {
                            it.copy(
                                status = false,
                                isLoading = false,
                                message = res.message
                            )
                        }
                    }
                }
            }
        }
    }

    private fun resendEmail() {
        viewModelScope.launch {
            authUseCase.resendEmail(
                createResendEmailVerificationDto = CreateResendEmailVerificationDto(
                    email = email
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
                                isLoading = false,
                                message = res.data?.message
                            )
                        }
                    }

                    is NetworkResult.Error -> {
                        _state.update {
                            it.copy(
                                status = false,
                                isLoading = false,
                                message = res.message
                            )
                        }
                    }
                }
            }
        }
    }
}