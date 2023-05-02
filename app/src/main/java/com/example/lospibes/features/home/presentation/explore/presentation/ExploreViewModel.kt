package com.example.lospibes.features.home.presentation.explore.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lospibes.features.home.data.dto.query.GetProductsQueryDto
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
    private val productUseCase: ProductUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(ExploreState())
    val state = _state.asStateFlow()

    private val _min = savedStateHandle.get<Int>("min")
    private val _max = savedStateHandle.get<Int>("max")

    private val _queryState = MutableStateFlow(
        QueryState(
            getProductsQueryDto = GetProductsQueryDto(
                name = savedStateHandle["name"],
                category = savedStateHandle["category"],
                min = if (_min != 0) _min else null,
                max = if (_max != 0) _max else null
            )
        )
    )
    val queryState = _queryState.asStateFlow()

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