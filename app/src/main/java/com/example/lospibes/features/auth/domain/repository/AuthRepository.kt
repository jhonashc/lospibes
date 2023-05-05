package com.example.lospibes.features.auth.domain.repository

import com.example.lospibes.features.auth.data.dto.body.CreateLoginDto
import com.example.lospibes.features.auth.data.dto.body.CreateRegisterDto
import com.example.lospibes.features.auth.data.dto.response.AuthResponse
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(
        createLoginDto: CreateLoginDto
    ): Flow<NetworkResult<AuthResponse>>

    fun register(
        createRegisterDto: CreateRegisterDto
    ): Flow<NetworkResult<AuthResponse>>
}