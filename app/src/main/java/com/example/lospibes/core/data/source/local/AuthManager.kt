package com.example.lospibes.core.data.source.local

import com.example.lospibes.core.domain.model.Auth
import kotlinx.coroutines.flow.Flow

interface AuthManager {
    fun getAuthManager(): Flow<Auth>
    suspend fun setAuthManager(auth: Auth)
    suspend fun deleteAuthManager()
}