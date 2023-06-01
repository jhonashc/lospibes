package com.example.lospibes.features.home.domain.use_case.address

data class AddressUseCase(
    val getAddresses: GetAddresses,
    val getUserAddress: GetUserAddress,
    val createUserAddress: CreateUserAddress,
    val updateUserAddress: UpdateUserAddress,
    val deleteUserAddress: DeleteUserAddress
)