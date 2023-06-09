package com.example.lospibes.features.home.domain.use_case.product

data class ProductUseCase(
    val getProducts: GetProducts,
    val getSimilarProducts: GetSimilarProducts,
    val getProductById: GetProductById,
    val searchProducts: SearchProducts
)