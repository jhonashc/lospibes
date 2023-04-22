package com.example.lospibes.features.home.di

import com.example.lospibes.features.home.data.repository.ComboRepositoryImpl
import com.example.lospibes.features.home.data.source.remote.ComboService
import com.example.lospibes.features.home.domain.repository.ComboRepository
import com.example.lospibes.features.home.domain.use_case.combo.ComboUseCase
import com.example.lospibes.features.home.domain.use_case.combo.GetCombos
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ComboModule {
    @Singleton
    @Provides
    fun provideComboService(retrofit: Retrofit): ComboService {
        return retrofit.create(ComboService::class.java)
    }

    @Singleton
    @Provides
    fun provideComboRepository(comboService: ComboService): ComboRepository {
        return ComboRepositoryImpl(comboService)
    }

    @Singleton
    @Provides
    fun provideComboUseCase(comboRepository: ComboRepository): ComboUseCase {
        return ComboUseCase(
            getCombos = GetCombos(comboRepository)
        )
    }
}