package com.example.lospibes.features.home.di

import com.example.lospibes.features.home.data.repository.CartRepositoryImpl
import com.example.lospibes.features.home.domain.repository.CartRepository
import com.example.lospibes.features.home.domain.use_case.cart.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CartModule {
    @Singleton
    @Provides
    fun provideCartRepository(): CartRepository {
        return CartRepositoryImpl()
    }

    @Singleton
    @Provides
    fun provideCartUseCase(
        cartRepository: CartRepository
    ): CartUseCase {
        return CartUseCase(
            addToCart = AddToCart(cartRepository),
            removeFromCart = RemoveFromCart(cartRepository),
            addQuantity = AddQuantity(cartRepository),
            subtractQuantity = SubtractQuantity(cartRepository),
            removeAll = RemoveAll(cartRepository)
        )
    }
}