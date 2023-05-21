package com.example.lospibes.features.home.presentation.favorite.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lospibes.features.home.domain.model.SearchWidgetState
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

    fun onEvent(event: FavoriteEvent) {
        when (event) {
            is FavoriteEvent.EnteredSearchBarText -> {
                _state.update {
                    it.copy(
                        searchText = event.value
                    )
                }
            }

            is FavoriteEvent.OnSearchBarClick -> {
                _state.update {
                    it.copy(
                        searchWidgetState = SearchWidgetState.OPENED
                    )
                }
            }

            is FavoriteEvent.OnSearchBarClose -> {
                _state.update {
                    it.copy(
                        searchText = "",
                        searchWidgetState = SearchWidgetState.CLOSED
                    )
                }
            }
        }
    }

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
                                isLoading = true
                            )
                        }
                    }

                    is NetworkResult.Success -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                favoriteProductList = res.data?.data ?: emptyList()
                            )
                        }
                    }

                    is NetworkResult.Error -> {
                        _state.update {
                            it.copy(
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