package com.example.lospibes.core.data.source.local

import kotlinx.coroutines.flow.Flow

interface AuthPreference {
    fun getToken(): Flow<String>
    fun getUserId(): Flow<String>
    suspend fun setToken(token: String)
    suspend fun setUserId(userId: String)
}