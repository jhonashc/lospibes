package com.example.lospibes.features.home.presentation.explore.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lospibes.features.home.data.dto.query.GetProductsQueryDto
import com.example.lospibes.features.home.data.dto.query.SearchProductsQueryDto
import com.example.lospibes.features.home.domain.model.SearchWidgetState
import com.example.lospibes.features.home.domain.use_case.product.ProductUseCase
import com.example.lospibes.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val productUseCase: ProductUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(ExploreState())
    val state = _state.asStateFlow()

    fun onEvent(event: ExploreEvent) {
        when (event) {
            is ExploreEvent.EnteredActive -> {
                _state.update {
                    it.copy(
                        isActive = event.value
                    )
                }
            }

            is ExploreEvent.EnteredQueryName -> {
                _state.update {
                    it.copy(
                        query = event.value
                    )
                }
            }

            is ExploreEvent.EnteredQuery -> {
                _state.update {
                    it.copy(
                        searchProductsQueryDto = event.value
                    )
                }
            }

            is ExploreEvent.OnOpenBottomSheet -> {
                _state.update {
                    it.copy(
                        isOpenBottomSheet = true
                    )
                }
            }

            is ExploreEvent.OnHideBottomSheet -> {
                _state.update {
                    it.copy(
                        isOpenBottomSheet = false
                    )
                }
            }

            is ExploreEvent.OnSearchBarOpen -> {
                _state.update {
                    it.copy(
                        searchWidgetState = SearchWidgetState.OPENED
                    )
                }
            }

            is ExploreEvent.OnSearchBarClose -> {
                _state.update {
                    it.copy(
                        query = "",
                        searchProductList = emptyList(),
                        searchWidgetState = SearchWidgetState.CLOSED
                    )
                }
            }
        }
    }

    fun getProducts(
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
                                productList = res.data?.data ?: emptyList()
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

    fun searchProducts(
        searchProductsQueryDto: SearchProductsQueryDto? = null
    ) {
        viewModelScope.launch {
            productUseCase.searchProducts(
                searchProductsQueryDto = searchProductsQueryDto
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
                        Log.e("SEARCH: ", "${res.data?.data?.map { it.name }}")
                        _state.update {
                            it.copy(
                                status = true,
                                isLoading = false,
                                searchProductList = res.data?.data ?: emptyList()
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