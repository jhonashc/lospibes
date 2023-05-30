package com.example.lospibes.core.domain.use_case.user

import com.example.lospibes.core.data.dto.response.UserResponse
import com.example.lospibes.core.domain.repository.UserRepository
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class GetUserById(
    private val userRepository: UserRepository
) {
    operator fun invoke(
        id: String
    ): Flow<NetworkResult<UserResponse>> {
        return userRepository.getUserById(
            id = id
        )
    }
}