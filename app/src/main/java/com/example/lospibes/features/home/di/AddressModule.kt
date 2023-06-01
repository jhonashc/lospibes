package com.example.lospibes.features.home.di

import com.example.lospibes.features.home.data.repository.AddressRepositoryImpl
import com.example.lospibes.features.home.data.source.remote.AddressService
import com.example.lospibes.features.home.domain.repository.AddressRepository
import com.example.lospibes.features.home.domain.use_case.address.AddressUseCase
import com.example.lospibes.features.home.domain.use_case.address.CreateUserAddress
import com.example.lospibes.features.home.domain.use_case.address.DeleteUserAddress
import com.example.lospibes.features.home.domain.use_case.address.GetAddresses
import com.example.lospibes.features.home.domain.use_case.address.GetUserAddress
import com.example.lospibes.features.home.domain.use_case.address.UpdateUserAddress
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AddressModule {
    @Singleton
    @Provides
    fun provideAddressService(
        retrofit: Retrofit
    ): AddressService {
        return retrofit.create(AddressService::class.java)
    }

    @Singleton
    @Provides
    fun provideAddressRepository(
        addressService: AddressService
    ): AddressRepository {
        return AddressRepositoryImpl(addressService)
    }

    @Singleton
    @Provides
    fun provideAddressUseCase(
        addressRepository: AddressRepository
    ): AddressUseCase {
        return AddressUseCase(
            getAddresses = GetAddresses(addressRepository),
            getUserAddress = GetUserAddress(addressRepository),
            createUserAddress = CreateUserAddress(addressRepository),
            updateUserAddress = UpdateUserAddress(addressRepository),
            deleteUserAddress = DeleteUserAddress(addressRepository)
        )
    }
}