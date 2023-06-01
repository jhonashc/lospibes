package com.example.lospibes.features.home.presentation.address_detail.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lospibes.features.home.data.dto.data.CreateUserAddressDto
import com.example.lospibes.features.home.data.dto.data.UpdateUserAddressDto
import com.example.lospibes.features.home.domain.use_case.address.AddressUseCase
import com.example.lospibes.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressDetailViewModel @Inject constructor(
    private val addressUseCase: AddressUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(AddressDetailState())
    val state = _state.asStateFlow()

    private val _addressId: String = savedStateHandle["addressId"] ?: ""

    init {
        _state.update {
            it.copy(
                id = _addressId,
                isLoading = _addressId.isNotEmpty()
            )
        }
    }

    fun onEvent(event: AddressDetailEvent) {
        when (event) {
            is AddressDetailEvent.EnteredName -> {
                _state.update {
                    it.copy(
                        name = event.value
                    )
                }
            }

            is AddressDetailEvent.EnteredSideStreet -> {
                _state.update {
                    it.copy(
                        sideStreet = event.value
                    )
                }
            }

            is AddressDetailEvent.EnteredDeliveryInstruction -> {
                _state.update {
                    it.copy(
                        deliveryInstruction = event.value
                    )
                }
            }
        }
    }

    fun getUserAddress(
        userId: String,
        addressId: String
    ) {
        viewModelScope.launch {
            addressUseCase.getUserAddress(
                userId = userId,
                addressId = addressId
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
                                id = res.data?.data?.id ?: "",
                                name = res.data?.data?.name ?: "",
                                sideStreet = res.data?.data?.sideStreet ?: "",
                                deliveryInstruction = res.data?.data?.deliveryInstruction ?: "",
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

    fun createUserAddress(
        userId: String
    ) {
        viewModelScope.launch {
            addressUseCase.createUserAddress(
                userId = userId,
                createUserAddressDto = CreateUserAddressDto(
                    name = _state.value.name,
                    sideStreet = _state.value.sideStreet,
                    deliveryInstruction = _state.value.deliveryInstruction
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

    fun updateUserAddress(
        userId: String,
        addressId: String
    ) {
        viewModelScope.launch {
            addressUseCase.updateUserAddress(
                userId = userId,
                addressId = addressId,
                updateUserAddressDto = UpdateUserAddressDto(
                    name = _state.value.name,
                    sideStreet = _state.value.sideStreet,
                    deliveryInstruction = _state.value.deliveryInstruction
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

    fun deleteUserAddress(
        userId: String,
        addressId: String
    ) {
        viewModelScope.launch {
            addressUseCase.deleteUserAddress(
                userId = userId,
                addressId = addressId
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
}