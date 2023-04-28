package com.example.lospibes.features.home.presentation.combo.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lospibes.features.home.domain.use_case.combo.ComboUseCase
import com.example.lospibes.features.home.domain.use_case.favorite.FavoriteUseCase
import com.example.lospibes.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComboViewModel @Inject constructor(
    private val comboUseCase: ComboUseCase,
    private val favoriteUseCase: FavoriteUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(ComboState())
    val state = _state.asStateFlow()

    private val comboId: String = savedStateHandle.get<String>("comboId") ?: ""

    init {
        getComboById(
            comboId = comboId
        )
    }

    private fun getComboById(
        comboId: String
    ) {
        viewModelScope.launch {
            comboUseCase.getComboById(
                id = comboId
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
                                combo = res.data?.data,
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

    fun getFavoriteCombo(
        comboId: String,
        userId: String
    ) {
        viewModelScope.launch {
            favoriteUseCase.getFavoriteCombo(
                comboId = comboId,
                userId = userId
            ).collect { res ->
                when (res) {
                    is NetworkResult.Loading -> {
                        _state.update {
                            it.copy(
                                isFavoriteComboLoading = true
                            )
                        }
                    }

                    is NetworkResult.Success -> {
                        _state.update {
                            it.copy(
                                favoriteCombo = res.data?.data,
                                isFavoriteComboLoading = false
                            )
                        }
                    }

                    is NetworkResult.Error -> {
                        _state.update {
                            it.copy(
                                isFavoriteComboLoading = false
                            )
                        }
                    }
                }
            }
        }
    }
}