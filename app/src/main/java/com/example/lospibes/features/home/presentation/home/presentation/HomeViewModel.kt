package com.example.lospibes.features.home.presentation.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lospibes.features.home.domain.use_case.product.ProductUseCase
import com.example.lospibes.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productUseCase: ProductUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            productUseCase.getProducts().collect { res ->
                when (res) {
                    is NetworkResult.Loading -> {
                        _state.update {
                            it.copy(
                                isLoading = true
                            )
                        }
                    }

                    is NetworkResult.Success -> {
                        _state.update {
                            it.copy(
                                productList = res.data?.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                    }

                    is NetworkResult.Error -> {
                        _state.update {
                            it.copy(
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