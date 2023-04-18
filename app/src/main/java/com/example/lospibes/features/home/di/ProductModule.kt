package com.example.lospibes.features.home.di

import com.example.lospibes.features.home.data.repository.ProductRepositoryImpl
import com.example.lospibes.features.home.data.source.remote.ProductService
import com.example.lospibes.features.home.domain.repository.ProductRepository
import com.example.lospibes.features.home.domain.use_case.product.GetProductById
import com.example.lospibes.features.home.domain.use_case.product.GetProducts
import com.example.lospibes.features.home.domain.use_case.product.ProductUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProductModule {
    @Singleton
    @Provides
    fun provideProductService(retrofit: Retrofit): ProductService {
        return retrofit.create(ProductService::class.java)
    }

    @Singleton
    @Provides
    fun provideProductRepository(productService: ProductService): ProductRepository {
        return ProductRepositoryImpl(productService)
    }

    @Singleton
    @Provides
    fun provideProductUseCase(productRepository: ProductRepository): ProductUseCase {
        return ProductUseCase(
            getProducts = GetProducts(productRepository),
            getProductById = GetProductById(productRepository)
        )
    }
}