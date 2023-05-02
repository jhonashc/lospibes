package com.example.lospibes.features.home.presentation.favorite.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lospibes.features.home.domain.use_case.favorite.FavoriteUseCase
import com.example.lospibes.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteUseCase: FavoriteUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(FavoriteState())
    val state = _state.asStateFlow()

    fun getFavoriteProducts(
        userId: String
    ) {
        viewModelScope.launch {
            favoriteUseCase.getFavoriteProducts(
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
                                favoriteProductList = res.data?.data ?: emptyList(),
                                isFavoriteProductLoading = false
                            )
                        }
                    }

                    is NetworkResult.Error -> {
                        _state.update {
                            it.copy(
                                message = res.message,
                                isFavoriteProductLoading = false
                            )
                        }
                    }
                }
            }
        }
    }
}