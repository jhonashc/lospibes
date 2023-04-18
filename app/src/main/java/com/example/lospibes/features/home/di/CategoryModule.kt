package com.example.lospibes.features.home.di

import com.example.lospibes.features.home.data.repository.CategoryRepositoryImpl
import com.example.lospibes.features.home.data.source.remote.CategoryService
import com.example.lospibes.features.home.domain.repository.CategoryRepository
import com.example.lospibes.features.home.domain.use_case.category.CategoryUseCase
import com.example.lospibes.features.home.domain.use_case.category.GetCategories
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CategoryModule {
    @Singleton
    @Provides
    fun provideCategoryService(retrofit: Retrofit): CategoryService {
        return retrofit.create(CategoryService::class.java)
    }

    @Singleton
    @Provides
    fun provideCategoryRepository(categoryService: CategoryService): CategoryRepository {
        return CategoryRepositoryImpl(categoryService)
    }

    @Singleton
    @Provides
    fun provideCategoryUseCase(categoryRepository: CategoryRepository): CategoryUseCase {
        return CategoryUseCase(
            getCategories = GetCategories(categoryRepository)
        )
    }
}