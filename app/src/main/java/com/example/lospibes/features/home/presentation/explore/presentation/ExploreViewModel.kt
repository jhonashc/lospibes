package com.example.lospibes.features.home.presentation.explore.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lospibes.features.home.data.dto.query.GetCategoriesQueryDto
import com.example.lospibes.features.home.data.dto.query.GetCombosQueryDto
import com.example.lospibes.features.home.data.dto.query.GetProductsQueryDto
import com.example.lospibes.features.home.domain.use_case.category.CategoryUseCase
import com.example.lospibes.features.home.domain.use_case.combo.ComboUseCase
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
    private val categoryUseCase: CategoryUseCase,
    private val comboUseCase: ComboUseCase,
    private val productUseCase: ProductUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(ExploreState())
    val state = _state.asStateFlow()

    private val _queryState = MutableStateFlow(
        QueryState(
            getProductsQueryDto = GetProductsQueryDto(
                name = savedStateHandle.get<String>("name"),
                category = savedStateHandle.get<String>("category"),
                min = savedStateHandle.get<Int>("min"),
                max = savedStateHandle.get<Int>("max")
            )
        )
    )
    val queryState = _queryState.asStateFlow()

    fun onEvent(event: ExploreEvent) {
        when (event) {
            is ExploreEvent.SelectedCategory -> {
                _queryState.update {
                    it.copy(
                        getProductsQueryDto = GetProductsQueryDto(
                            category = event.value
                        )
                    )
                }
            }
        }
    }

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

    fun getCombos(
        getCombosQueryDto: GetCombosQueryDto? = null
    ) {
        viewModelScope.launch {
            comboUseCase.getCombos(
                getCombosQueryDto = getCombosQueryDto
            ).collect { res ->
                when (res) {
                    is NetworkResult.Loading -> {
                        _state.update {
                            it.copy(
                                isComboLoading = true
                            )
                        }
                    }

                    is NetworkResult.Success -> {
                        _state.update {
                            it.copy(
                                comboList = res.data?.data ?: emptyList(),
                                productList = emptyList(),
                                isComboLoading = false
                            )
                        }
                    }

                    is NetworkResult.Error -> {
                        _state.update {
                            it.copy(
                                message = res.message,
                                isComboLoading = false
                            )
                        }
                    }
                }
            }
        }
    }

    fun getProducts(
        getProductsQueryDto: GetProductsQueryDto
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
                                comboList = emptyList(),
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