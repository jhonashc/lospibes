package com.example.lospibes.core.di

import com.example.lospibes.core.data.repository.UserRepositoryImpl
import com.example.lospibes.core.data.source.remote.UserService
import com.example.lospibes.core.domain.repository.UserRepository
import com.example.lospibes.core.domain.use_case.user.GetUserById
import com.example.lospibes.core.domain.use_case.user.UserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {
    @Singleton
    @Provides
    fun provideUserService(
        retrofit: Retrofit
    ): UserService {
        return retrofit.create(UserService::class.java)
    }

    @Singleton
    @Provides
    fun provideUserRepository(
        userService: UserService
    ): UserRepository {
        return UserRepositoryImpl(userService)
    }

    @Singleton
    @Provides
    fun provideUserUseCase(
        userRepository: UserRepository
    ): UserUseCase {
        return UserUseCase(
            getUserById = GetUserById(userRepository)
        )
    }
}