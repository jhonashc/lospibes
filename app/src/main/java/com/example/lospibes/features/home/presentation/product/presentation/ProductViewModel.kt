package com.example.lospibes.features.home.presentation.product.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lospibes.features.home.data.dto.query.GetProductsQueryDto
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

    private val _productId: String = savedStateHandle.get<String>("productId") ?: ""

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
                                isProductLoading = true
                            )
                        }
                    }

                    is NetworkResult.Success -> {
                        _state.update {
                            it.copy(
                                product = res.data?.data,
                                isProductLoading = false
                            )
                        }
                    }

                    is NetworkResult.Error -> {
                        _state.update {
                            it.copy(
                                message = res.message,
                                isProductLoading = false
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
                                isFavoriteProductLoading = true
                            )
                        }
                    }

                    is NetworkResult.Success -> {
                        _state.update {
                            it.copy(
                                favoriteProduct = res.data?.data,
                                isFavoriteProductLoading = false
                            )
                        }
                    }

                    is NetworkResult.Error -> {
                        _state.update {
                            it.copy(
                                isFavoriteProductLoading = false
                            )
                        }
                    }
                }
            }
        }
    }

    fun getSimilarProducts(
        getProductsQueryDto: GetProductsQueryDto? = null
    ) {
        viewModelScope.launch {
            productUseCase.getProducts(
                getProductsQueryDto = getProductsQueryDto
            ).collect { res ->
                when (res) {
                    is NetworkResult.Loading -> {
                        _state.update {
                            it.copy(
                                isSimilarProductLoading = true
                            )
                        }
                    }

                    is NetworkResult.Success -> {
                        _state.update {
                            it.copy(
                                similarProductList = res.data?.data ?: emptyList(),
                                isSimilarProductLoading = false
                            )
                        }
                    }

                    is NetworkResult.Error -> {
                        _state.update {
                            it.copy(
                                message = res.message,
                                isSimilarProductLoading = false
                            )
                        }
                    }
                }
            }
        }
    }
}