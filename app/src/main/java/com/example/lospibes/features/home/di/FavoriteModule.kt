package com.example.lospibes.features.home.di

import com.example.lospibes.features.home.data.repository.FavoriteRepositoryImpl
import com.example.lospibes.features.home.data.source.remote.FavoriteService
import com.example.lospibes.features.home.domain.repository.FavoriteRepository
import com.example.lospibes.features.home.domain.use_case.favorite.FavoriteUseCase
import com.example.lospibes.features.home.domain.use_case.favorite.GetFavoriteProduct
import com.example.lospibes.features.home.domain.use_case.favorite.GetFavoriteProducts
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavoriteModule {
    @Singleton
    @Provides
    fun provideFavoriteService(
        retrofit: Retrofit
    ): FavoriteService {
        return retrofit.create(FavoriteService::class.java)
    }

    @Singleton
    @Provides
    fun provideFavoriteRepository(
        favoriteService: FavoriteService
    ): FavoriteRepository {
        return FavoriteRepositoryImpl(
            favoriteService = favoriteService
        )
    }

    @Singleton
    @Provides
    fun provideFavoriteUseCase(
        favoriteRepository: FavoriteRepository
    ): FavoriteUseCase {
        return FavoriteUseCase(
            getFavoriteProducts = GetFavoriteProducts(
                favoriteRepository = favoriteRepository
            ),
            getFavoriteProduct = GetFavoriteProduct(
                favoriteRepository = favoriteRepository
            )
        )
    }
}