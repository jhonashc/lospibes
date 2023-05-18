package com.example.lospibes.features.home.data.repository

import com.example.lospibes.features.home.data.dto.response.UserResponse
import com.example.lospibes.features.home.data.source.remote.UserService
import com.example.lospibes.features.home.domain.repository.UserRepository
import com.example.lospibes.utils.BaseApiResponse
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService
) : UserRepository, BaseApiResponse() {
    override fun getUserById(
        id: String
    ): Flow<NetworkResult<UserResponse>> {
        return safeApiCall { userService.getUserById(id) }
    }
}