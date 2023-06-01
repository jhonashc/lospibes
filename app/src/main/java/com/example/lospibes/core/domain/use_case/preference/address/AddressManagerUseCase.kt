package com.example.lospibes.core.domain.use_case.preference.address

data class AddressManagerUseCase(
    val getAddressManager: GetAddressManager,
    val setAddressManager: SetAddressManager,
    val deleteAddressManager: DeleteAddressManager
)
