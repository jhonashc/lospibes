package com.example.lospibes.features.home.presentation.profile_detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lospibes.core.domain.use_case.user.UserUseCase
import com.example.lospibes.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileDetailViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(ProfileDetailState())
    val state = _state.asStateFlow()

    fun onEvent(event: ProfileDetailEvent) {
        when (event) {
            is ProfileDetailEvent.EnteredUsername -> {
                _state.update {
                    it.copy(
                        username = event.value
                    )
                }
            }

            is ProfileDetailEvent.EnteredFirstname -> {
                _state.update {
                    it.copy(
                        firstName = event.value
                    )
                }
            }

            is ProfileDetailEvent.EnteredLastname -> {
                _state.update {
                    it.copy(
                        lastName = event.value
                    )
                }
            }

            is ProfileDetailEvent.EnteredTelephone -> {
                _state.update {
                    it.copy(
                        telephone = event.value
                    )
                }
            }

            is ProfileDetailEvent.EnteredSelectedImageUri -> {
                _state.update {
                    it.copy(
                        selectedImageUri = event.value
                    )
                }
            }
        }
    }

    fun getUserById(
        userId: String
    ) {
        viewModelScope.launch {
            userUseCase.getUserById(
                id = userId
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
                                user = res.data?.data,
                                username = res.data?.data?.username ?: "",
                                firstName = res.data?.data?.person?.firstName ?: "",
                                lastName = res.data?.data?.person?.lastName ?: "",
                                telephone = res.data?.data?.person?.telephone ?: "",
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