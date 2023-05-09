package com.example.lospibes.features.home.domain.repository

import com.example.lospibes.features.home.data.dto.response.UserResponse
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUserById(
        id: String
    ): Flow<NetworkResult<UserResponse>>
}