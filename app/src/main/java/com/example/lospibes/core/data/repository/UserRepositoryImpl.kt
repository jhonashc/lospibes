package com.example.lospibes.core.data.repository

import com.example.lospibes.core.data.dto.response.UserResponse
import com.example.lospibes.core.data.source.remote.UserService
import com.example.lospibes.core.domain.repository.UserRepository
import com.example.lospibes.utils.BaseApiResponse
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService
) : UserRepository, BaseApiResponse() {
    override fun getUserById(
        id: String
    ): Flow<NetworkResult<UserResponse>> {
        return safeApiCall {
            userService.getUserById(id)
        }.flowOn(Dispatchers.IO)
    }
}