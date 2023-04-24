package com.example.lospibes.features.home.viewmodel.cart

import androidx.lifecycle.ViewModel
import com.example.lospibes.features.home.domain.model.CartItem
import com.example.lospibes.features.home.domain.use_case.cart.CartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartUseCase: CartUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(CartState())
    val state = _state.asStateFlow()

    fun addToCart(
        cartItem: CartItem
    ) {
        _state.update {
            it.copy(
                cartItemList = cartUseCase.addToCart(cartItem)
            )
        }
    }
}