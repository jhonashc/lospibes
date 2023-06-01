package com.example.lospibes.features.home.presentation.address.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lospibes.features.home.data.dto.query.GetAddressQueryDto
import com.example.lospibes.features.home.domain.use_case.address.AddressUseCase
import com.example.lospibes.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(
    private val addressUseCase: AddressUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(AddressState())
    val state = _state.asStateFlow()

    fun getAddresses(
        userId: String,
        getAddressQueryDto: GetAddressQueryDto? = null
    ) {
        viewModelScope.launch {
            addressUseCase.getAddresses(
                userId = userId,
                getAddressQueryDto = getAddressQueryDto
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
                                addressList = res.data?.data ?: emptyList()
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