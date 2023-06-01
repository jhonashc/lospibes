package com.example.lospibes.features.home.presentation.address_detail.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.lospibes.features.home.domain.use_case.address.AddressUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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

            is AddressDetailEvent.EnteredAddressReference -> {
                _state.update {
                    it.copy(
                        addressReference = event.value
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
}