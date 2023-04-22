package com.example.lospibes.features.home.data.repository

import com.example.lospibes.features.home.data.dto.query.GetCombosQueryDto
import com.example.lospibes.features.home.data.dto.response.ComboResponse
import com.example.lospibes.features.home.data.dto.response.CombosResponse
import com.example.lospibes.features.home.data.source.remote.ComboService
import com.example.lospibes.features.home.domain.repository.ComboRepository
import com.example.lospibes.utils.BaseApiResponse
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ComboRepositoryImpl @Inject constructor(
    private val comboService: ComboService
) : ComboRepository, BaseApiResponse() {
    override fun getCombos(
        getCombosQueryDto: GetCombosQueryDto?
    ): Flow<NetworkResult<CombosResponse>> {
        return safeApiCall {
            comboService.getCombos(
                name = getCombosQueryDto?.name,
                min = getCombosQueryDto?.min,
                max = getCombosQueryDto?.max,
                limit = getCombosQueryDto?.limit,
                offset = getCombosQueryDto?.offset
            )
        }.flowOn(Dispatchers.IO)
    }

    override fun getComboById(
        id: String
    ): Flow<NetworkResult<ComboResponse>> {
        return safeApiCall {
            comboService.getComboById(id)
        }.flowOn(Dispatchers.IO)
    }
}