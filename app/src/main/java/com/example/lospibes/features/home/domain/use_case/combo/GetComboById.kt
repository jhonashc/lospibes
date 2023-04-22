package com.example.lospibes.features.home.domain.use_case.combo

import com.example.lospibes.features.home.data.dto.response.ComboResponse
import com.example.lospibes.features.home.domain.repository.ComboRepository
import com.example.lospibes.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class GetComboById(
    private val comboRepository: ComboRepository
) {
    operator fun invoke(
        id: String
    ): Flow<NetworkResult<ComboResponse>> {
        return comboRepository.getComboById(id)
    }
}