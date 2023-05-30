package com.example.lospibes.core.domain.repository

import com.example.lospibes.core.data.dto.response.UserResponse
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUserById(
        id: String
    ): Flow<NetworkResult<UserResponse>>
}