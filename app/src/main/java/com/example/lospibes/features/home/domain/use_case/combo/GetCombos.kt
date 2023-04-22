package com.example.lospibes.features.home.domain.use_case.combo

import com.example.lospibes.features.home.data.dto.query.GetCombosQueryDto
import com.example.lospibes.features.home.data.dto.response.ComboResponse
import com.example.lospibes.features.home.domain.repository.ComboRepository
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class GetCombos(
    private val comboRepository: ComboRepository
) {
    operator fun invoke(
        getCombosQueryDto: GetCombosQueryDto? = null
    ): Flow<NetworkResult<ComboResponse>> {
        return comboRepository.getCombos(getCombosQueryDto)
    }
}