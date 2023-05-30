package com.example.lospibes.features.home.presentation.product.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lospibes.features.home.data.dto.query.GetSimilarProductsQueryDto
import com.example.lospibes.features.home.domain.use_case.favorite.FavoriteUseCase
import com.example.lospibes.features.home.domain.use_case.product.ProductUseCase
import com.example.lospibes.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productUseCase: ProductUseCase,
    private val favoriteUseCase: FavoriteUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(ProductState())
    val state = _state.asStateFlow()

    private val _productId: String = savedStateHandle["productId"] ?: ""

    init {
        getProductById(
            productId = _productId
        )
    }

    private fun getProductById(
        productId: String
    ) {
        viewModelScope.launch {
            productUseCase.getProductById(
                id = productId
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
                                product = res.data?.data
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

    fun getFavoriteProduct(
        productId: String,
        userId: String
    ) {
        viewModelScope.launch {
            favoriteUseCase.getFavoriteProduct(
                productId = productId,
                userId = userId
            ).collect { res ->
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
                                isLoading = false,
                                favoriteProduct = res.data?.data
                            )
                        }
                    }

                    is NetworkResult.Error -> {
                        _state.update {
                            it.copy(
                                isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }

    fun getSimilarProducts(
        productId: String,
        getSimilarProductsQueryDto: GetSimilarProductsQueryDto? = null
    ) {
        viewModelScope.launch {
            productUseCase.getSimilarProducts(
                id = productId,
                getSimilarProductsQueryDto = getSimilarProductsQueryDto
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
                                similarProductList = res.data?.data ?: emptyList()
                            )
                        }
                    }

                    is NetworkResult.Error -> {
                        _state.update {
                            it.copy(
                                status = false,
                                isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }
}