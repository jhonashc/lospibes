package com.example.lospibes.utils

import com.example.lospibes.common.domain.model.Category
import com.example.lospibes.common.domain.model.Product
import java.util.*

object Constants {
    const val ROOT_GRAPH_ROUTE = "root_graph"
    const val AUTH_GRAPH_ROUTE = "auth_graph"
    const val HOME_GRAPH_ROUTE = "home_graph"

    // Temporal
    val products: List<Product> = listOf(
        Product(
            id = "1",
            name = "Bacon burger",
            description = "The best burger",
            imageUrl = "https://burgerkingec.com/wp-content/uploads/2020/04/bbq-bacon-king-img.png",
            price = 3.5,
            stock = 100
        ),
        Product(
            id = "2",
            name = "Chicken day",
            description = "The best chicken day",
            imageUrl = "https://pimagerepository.churchstexaschicken.com/73929f03-04d8-48ae-b690-de199b19aba8_actual.png",
            price = 6.5,
            stock = 100
        ),
        Product(
            id = "3",
            name = "Spice Wings",
            description = "The best spice wings",
            imageUrl = "https://static.wixstatic.com/media/f9728f_a8ffafb7270348918ac0f2cd81d1d27e~mv2_d_5184_3456_s_4_2.png/v1/fill/w_520,h_346,al_c,q_85,usm_0.66_1.00_0.01,enc_auto/Boneless_personal_recorte1.png",
            price = 6.5,
            stock = 100
        )
    )

    private val date = Date()
    val categories: List<Category> = listOf(
        Category(
            id = "100",
            name = "All",
            createdAt = date,
            updatedAt = date
        ),
        Category(
            id = "1",
            name = "Hamburger",
            createdAt = date,
            updatedAt = date
        ),
        Category(
            id = "2",
            name = "Drinks",
            createdAt = date,
            updatedAt = date
        ),
        Category(
            id = "3",
            name = "Pizza",
            createdAt = date,
            updatedAt = date
        ),
        Category(
            id = "4",
            name = "Hot dogs",
            createdAt = date,
            updatedAt = date
        ),
        Category(
            id = "5",
            name = "Tacos",
            createdAt = date,
            updatedAt = date
        ),
        Category(
            id = "46",
            name = "Wings",
            createdAt = date,
            updatedAt = date
        )
    )
}