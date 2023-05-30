package com.example.lospibes.features.home.di

import com.example.lospibes.features.home.data.repository.PromotionRepositoryImpl
import com.example.lospibes.features.home.data.source.remote.PromotionService
import com.example.lospibes.features.home.domain.repository.PromotionRepository
import com.example.lospibes.features.home.domain.use_case.promotion.GetPromotions
import com.example.lospibes.features.home.domain.use_case.promotion.PromotionUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PromotionModule {
    @Singleton
    @Provides
    fun providePromotionService(
        retrofit: Retrofit
    ): PromotionService {
        return retrofit.create(PromotionService::class.java)
    }

    @Singleton
    @Provides
    fun providePromotionRepository(
        promotionService: PromotionService
    ): PromotionRepository {
        return PromotionRepositoryImpl(promotionService)
    }

    @Singleton
    @Provides
    fun providePromotionUseCase(
        promotionRepository: PromotionRepository
    ): PromotionUseCase {
        return PromotionUseCase(
            getPromotions = GetPromotions(promotionRepository)
        )
    }
}