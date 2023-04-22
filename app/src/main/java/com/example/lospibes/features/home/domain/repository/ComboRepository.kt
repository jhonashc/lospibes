package com.example.lospibes.features.home.domain.repository

import com.example.lospibes.features.home.data.dto.query.GetCombosQueryDto
import com.example.lospibes.features.home.data.dto.response.ComboResponse
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface ComboRepository {
    fun getCombos(
        getCombosQueryDto: GetCombosQueryDto? = null
    ): Flow<NetworkResult<ComboResponse>>
}