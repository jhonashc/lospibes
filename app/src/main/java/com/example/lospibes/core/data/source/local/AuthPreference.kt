package com.example.lospibes.core.data.source.local

import com.example.lospibes.core.domain.model.Auth
import kotlinx.coroutines.flow.Flow

interface AuthPreference {
    fun getAuthPreference(): Flow<Auth>
    suspend fun setAuthPreference(auth: Auth)
}