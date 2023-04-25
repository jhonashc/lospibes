package com.example.lospibes.features.home.viewmodel.cart

import androidx.lifecycle.ViewModel
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

    fun onEvent(event: CartEvent) {
        when (event) {
            is CartEvent.AddToCart -> {
                _state.update {
                    it.copy(
                        cartItemList = cartUseCase.addToCart(event.cartItem)
                    )
                }
            }

            is CartEvent.RemoveFromCart -> {
                _state.update {
                    it.copy(
                        cartItemList = cartUseCase.removeFromCart(event.cartItem)
                    )
                }
            }

            is CartEvent.AddQuantity -> {
                _state.update {
                    it.copy(
                        cartItemList = cartUseCase.addQuantity(event.cartItem)
                    )
                }
            }

            is CartEvent.SubtractQuantity -> {
                _state.update {
                    it.copy(
                        cartItemList = cartUseCase.subtractQuantity(event.cartItem)
                    )
                }
            }

            is CartEvent.RemoveAll -> {
                _state.update {
                    it.copy(
                        cartItemList = cartUseCase.removeAll()
                    )
                }
            }
        }
    }
}