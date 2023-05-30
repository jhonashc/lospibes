package com.example.lospibes.features.home.presentation.explore.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lospibes.features.home.data.dto.query.GetCategoriesQueryDto
import com.example.lospibes.features.home.domain.use_case.category.CategoryUseCase
import com.example.lospibes.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreBottomViewModel @Inject constructor(
    private val categoryUseCase: CategoryUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(ExploreBottomState())
    val state = _state.asStateFlow()

    init {
        getCategories()
    }

    fun onEvent(event: ExploreBottomEvent) {
        when (event) {
            is ExploreBottomEvent.EnteredCategory -> {
                _state.update {
                    it.copy(
                        category = event.value
                    )
                }
            }

            is ExploreBottomEvent.EnteredRange -> {
                _state.update {
                    it.copy(
                        range = event.value
                    )
                }
            }

            is ExploreBottomEvent.OnResetQuery -> {
                _state.update {
                    it.copy(
                        category = "",
                        range = 1f..25f
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
                                status = false,
                                isLoading = true
                            )
                        }
                    }

                    is NetworkResult.Success -> {
                        _state.update {
                            it.copy(
                                status = false,
                                isLoading = false,
                                categoryList = res.data?.data ?: emptyList()
                            )
                        }
                    }

                    is NetworkResult.Error -> {
                        _state.update {
                            it.copy(
                                status = true,
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