package com.example.lospibes.utils

import com.example.lospibes.features.home.domain.model.Category
import com.example.lospibes.features.home.domain.model.Product
import java.util.Date

object Constants {
    const val ROOT_GRAPH_ROUTE = "root_graph"
    const val AUTH_GRAPH_ROUTE = "auth_graph"
    const val HOME_GRAPH_ROUTE = "home_graph"
    const val DETAIL_GRAPH_ROUTE = "detail_graph"

    const val CATEGORIES = "categories"
    const val COMBOS = "combos"
    const val FAVORITES = "favorites"
    const val PRODUCTS = "products"

    // Temporal
    private val date = Date()

    val categories: List<Category> = listOf(
        Category(
            id = "100",
            name = "Todas",
            createdAt = date,
            updatedAt = date
        ),
        Category(
            id = "1",
            name = "Hamburguesas",
            createdAt = date,
            updatedAt = date
        ),
        Category(
            id = "2",
            name = "Bebidas",
            createdAt = date,
            updatedAt = date
        ),
        Category(
            id = "3",
            name = "Pizzas",
            createdAt = date,
            updatedAt = date
        ), Category(
            id = "4",
            name = "Cócteles",
            createdAt = date,
            updatedAt = date
        ),
        Category(
            id = "5",
            name = "Hot dogs",
            createdAt = date,
            updatedAt = date
        ),
        Category(
            id = "6",
            name = "Tacos",
            createdAt = date,
            updatedAt = date
        ),
        Category(
            id = "46",
            name = "Alitas",
            createdAt = date,
            updatedAt = date
        )
    )

    val products: List<Product> = listOf(
        Product(
            id = "1",
            name = "Bacon burger",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis faucibus imperdiet odio, in semper risus faucibus ut. Praesent lacinia augue quis lacus posuere dictum. Morbi placerat libero felis, id gravida velit cursus ut. Quisque tempus bibendum urna, non maximus justo.",
            imageUrl = "https://burgerkingec.com/wp-content/uploads/2020/04/bbq-bacon-king-img.png",
            price = 3.50,
            stock = 100,
            categories = categories,
            createdAt = date,
            updatedAt = date
        ),
        Product(
            id = "2",
            name = "Chicken day",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis faucibus imperdiet odio, in semper risus faucibus ut. Praesent lacinia augue quis lacus posuere dictum. Morbi placerat libero felis, id gravida velit cursus ut. Quisque tempus bibendum urna, non maximus justo.",
            imageUrl = "https://pimagerepository.churchstexaschicken.com/73929f03-04d8-48ae-b690-de199b19aba8_actual.png",
            price = 6.50,
            stock = 100,
            categories = categories,
            createdAt = date,
            updatedAt = date
        ),
        Product(
            id = "3",
            name = "Spice Wings",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis faucibus imperdiet odio, in semper risus faucibus ut. Praesent lacinia augue quis lacus posuere dictum. Morbi placerat libero felis, id gravida velit cursus ut. Quisque tempus bibendum urna, non maximus justo.",
            imageUrl = "https://static.wixstatic.com/media/f9728f_a8ffafb7270348918ac0f2cd81d1d27e~mv2_d_5184_3456_s_4_2.png/v1/fill/w_520,h_346,al_c,q_85,usm_0.66_1.00_0.01,enc_auto/Boneless_personal_recorte1.png",
            price = 6.50,
            stock = 100,
            categories = categories,
            createdAt = date,
            updatedAt = date
        ),
        Product(
            id = "4",
            name = "Peppers Pizza",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis faucibus imperdiet odio, in semper risus faucibus ut. Praesent lacinia augue quis lacus posuere dictum. Morbi placerat libero felis, id gravida velit cursus ut. Quisque tempus bibendum urna, non maximus justo.",
            imageUrl = "https://underpizza-staging-image-storage.s3.us-east-2.amazonaws.com/sides/dEcc48jAvLAM5t2KY/1.png",
            price = 7.0,
            stock = 100,
            categories = categories,
            createdAt = date,
            updatedAt = date
        ),
        Product(
            id = "5",
            name = "Chili Dog",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis faucibus imperdiet odio, in semper risus faucibus ut. Praesent lacinia augue quis lacus posuere dictum. Morbi placerat libero felis, id gravida velit cursus ut. Quisque tempus bibendum urna, non maximus justo.",
            imageUrl = "https://livingonthecheap.com/lotc-cms/wp-content/uploads/2017/07/unnamed-1-e1507716136762.png",
            price = 3.25,
            stock = 100,
            categories = categories,
            createdAt = date,
            updatedAt = date
        ),
        Product(
            id = "6",
            name = "Chocolate Pastel",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis faucibus imperdiet odio, in semper risus faucibus ut. Praesent lacinia augue quis lacus posuere dictum. Morbi placerat libero felis, id gravida velit cursus ut. Quisque tempus bibendum urna, non maximus justo.",
            imageUrl = "https://burgerkingec.com/wp-content/uploads/2020/05/Hersheys-Sundae-Pie-768x768.png",
            price = 3.0,
            stock = 100,
            categories = categories,
            createdAt = date,
            updatedAt = date
        )
    )
}