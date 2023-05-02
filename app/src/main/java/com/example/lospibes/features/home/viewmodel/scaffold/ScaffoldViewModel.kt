package com.example.lospibes.features.home.viewmodel.scaffold

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ScaffoldViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(ScaffoldState())
    val state = _state.asStateFlow()

    fun onEvent(event: ScaffoldEvent) {
        when (event) {
            is ScaffoldEvent.ShowBottomBar -> {
                _state.update {
                    it.copy(
                        showBottomBar = true
                    )
                }
            }

            is ScaffoldEvent.HideBottomBar -> {
                _state.update {
                    it.copy(
                        showBottomBar = false
                    )
                }
            }
        }
    }
}