package com.example.lospibes.features.auth.di

import com.example.lospibes.features.auth.data.repository.AuthRepositoryImpl
import com.example.lospibes.features.auth.data.source.remote.AuthService
import com.example.lospibes.features.auth.domain.repository.AuthRepository
import com.example.lospibes.features.auth.domain.use_case.AuthUseCase
import com.example.lospibes.features.auth.domain.use_case.Login
import com.example.lospibes.features.auth.domain.use_case.Register
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    @Singleton
    @Provides
    fun provideAuthService(
        retrofit: Retrofit
    ): AuthService {
        return retrofit.create(AuthService::class.java)
    }

    @Singleton
    @Provides
    fun provideAuthRepository(
        authService: AuthService
    ): AuthRepository {
        return AuthRepositoryImpl(authService)
    }

    @Singleton
    @Provides
    fun provideAuthUseCase(
        authRepository: AuthRepository
    ): AuthUseCase {
        return AuthUseCase(
            login = Login(authRepository),
            register = Register(authRepository)
        )
    }
}