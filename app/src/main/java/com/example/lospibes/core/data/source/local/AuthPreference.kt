package com.example.lospibes.core.data.source.local

import kotlinx.coroutines.flow.Flow

interface AuthPreference {
    fun getAccessToken(): Flow<String>
    fun getRefreshToken(): Flow<String>
    fun getUserId(): Flow<String>
    suspend fun setAccessToken(accessToken: String)
    suspend fun setRefreshToken(refreshToken: String)
    suspend fun setUserId(userId: String)
}