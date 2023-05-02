package com.example.lospibes.features.home.presentation.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lospibes.features.home.data.dto.query.GetCategoriesQueryDto
import com.example.lospibes.features.home.data.dto.query.GetProductsQueryDto
import com.example.lospibes.features.home.domain.use_case.category.CategoryUseCase
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
    private val categoryUseCase: CategoryUseCase,
    private val productUseCase: ProductUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    fun getCategories(
        getCategoriesQueryDto: GetCategoriesQueryDto? = null
    ) {
        viewModelScope.launch {
            categoryUseCase.getCategories(
                getCategoriesQueryDto = getCategoriesQueryDto
            ).collect { res ->
                when (res) {
                    is NetworkResult.Loading -> {
                        _state.update {
                            it.copy(
                                isCategoryLoading = true
                            )
                        }
                    }

                    is NetworkResult.Success -> {
                        _state.update {
                            it.copy(
                                categoryList = res.data?.data ?: emptyList(),
                                isCategoryLoading = false,
                            )
                        }
                    }

                    is NetworkResult.Error -> {
                        _state.update {
                            it.copy(
                                message = res.message,
                                isCategoryLoading = false
                            )
                        }
                    }
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
                                isProductLoading = true
                            )
                        }
                    }

                    is NetworkResult.Success -> {
                        _state.update {
                            it.copy(
                                productList = res.data?.data ?: emptyList(),
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
}