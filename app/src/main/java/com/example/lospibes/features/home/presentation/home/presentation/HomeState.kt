package com.example.lospibes.features.home.presentation.home.presentation

import com.example.lospibes.features.home.domain.model.Address
import com.example.lospibes.features.home.domain.model.Category
import com.example.lospibes.features.home.domain.model.Product
import com.example.lospibes.features.home.domain.model.TabItem

data class HomeState(
    val status: Boolean = true,
    val message: String? = null,
    val isLoading: Boolean = true,
    val isOpenBottomSheet: Boolean = false,
    val selectedTab: TabItem = TabItem("Todas"),
    val addressList: List<Address> = emptyList(),
    val categoryList: List<Category> = emptyList(),
    val productList: List<Product> = emptyList(),
    val popularProductList: List<Product> = emptyList()
)
