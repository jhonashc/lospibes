package com.example.lospibes.utils

import com.example.lospibes.common.domain.model.Category
import com.example.lospibes.common.domain.model.Combo
import com.example.lospibes.common.domain.model.Product
import java.util.*

object Constants {
    const val ROOT_GRAPH_ROUTE = "root_graph"
    const val AUTH_GRAPH_ROUTE = "auth_graph"
    const val HOME_GRAPH_ROUTE = "home_graph"
    const val DETAIL_GRAPH_ROUTE = "detail_graph"

    // Temporal
    private val date = Date()

    val categories: List<Category> = listOf(
        Category(
            id = "100",
            name = "All",
            code = "\uD83D\uDECE️",
            createdAt = date,
            updatedAt = date
        ),
        Category(
            id = "1",
            name = "Hamburger",
            code = "\uD83C\uDF54",
            createdAt = date,
            updatedAt = date
        ),
        Category(
            id = "2",
            name = "Drinks",
            code = "\uD83E\uDD64",
            createdAt = date,
            updatedAt = date
        ),
        Category(
            id = "3",
            name = "Pizza",
            code = "\uD83C\uDF55",
            createdAt = date,
            updatedAt = date
        ), Category(
            id = "4",
            name = "Cocktail",
            code = "\uD83C\uDF79",
            createdAt = date,
            updatedAt = date
        ),
        Category(
            id = "5",
            name = "Hot dogs",
            code = "\uD83C\uDF2D",
            createdAt = date,
            updatedAt = date
        ),
        Category(
            id = "6",
            name = "Tacos",
            code = "\uD83C\uDF2E",
            createdAt = date,
            updatedAt = date
        ),
        Category(
            id = "46",
            name = "Wings",
            code = "\uD83C\uDF57",
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
            categories = categories
        ),
        Product(
            id = "2",
            name = "Chicken day",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis faucibus imperdiet odio, in semper risus faucibus ut. Praesent lacinia augue quis lacus posuere dictum. Morbi placerat libero felis, id gravida velit cursus ut. Quisque tempus bibendum urna, non maximus justo.",
            imageUrl = "https://pimagerepository.churchstexaschicken.com/73929f03-04d8-48ae-b690-de199b19aba8_actual.png",
            price = 6.50,
            stock = 100,
            categories = categories
        ),
        Product(
            id = "3",
            name = "Spice Wings",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis faucibus imperdiet odio, in semper risus faucibus ut. Praesent lacinia augue quis lacus posuere dictum. Morbi placerat libero felis, id gravida velit cursus ut. Quisque tempus bibendum urna, non maximus justo.",
            imageUrl = "https://static.wixstatic.com/media/f9728f_a8ffafb7270348918ac0f2cd81d1d27e~mv2_d_5184_3456_s_4_2.png/v1/fill/w_520,h_346,al_c,q_85,usm_0.66_1.00_0.01,enc_auto/Boneless_personal_recorte1.png",
            price = 6.50,
            stock = 100,
            categories = categories
        ),
        Product(
            id = "4",
            name = "Peppers Pizza",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis faucibus imperdiet odio, in semper risus faucibus ut. Praesent lacinia augue quis lacus posuere dictum. Morbi placerat libero felis, id gravida velit cursus ut. Quisque tempus bibendum urna, non maximus justo.",
            imageUrl = "https://underpizza-staging-image-storage.s3.us-east-2.amazonaws.com/sides/dEcc48jAvLAM5t2KY/1.png",
            price = 7.0,
            stock = 100,
            categories = categories
        ),
        Product(
            id = "5",
            name = "Chili Dog",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis faucibus imperdiet odio, in semper risus faucibus ut. Praesent lacinia augue quis lacus posuere dictum. Morbi placerat libero felis, id gravida velit cursus ut. Quisque tempus bibendum urna, non maximus justo.",
            imageUrl = "https://livingonthecheap.com/lotc-cms/wp-content/uploads/2017/07/unnamed-1-e1507716136762.png",
            price = 3.25,
            stock = 100,
            categories = categories
        ),
        Product(
            id = "6",
            name = "Chocolate Pastel",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis faucibus imperdiet odio, in semper risus faucibus ut. Praesent lacinia augue quis lacus posuere dictum. Morbi placerat libero felis, id gravida velit cursus ut. Quisque tempus bibendum urna, non maximus justo.",
            imageUrl = "https://burgerkingec.com/wp-content/uploads/2020/05/Hersheys-Sundae-Pie-768x768.png",
            price = 3.0,
            stock = 100,
            categories = categories
        )
    )

    val hamburgers: List<Product> = listOf(
        Product(
            id = "2",
            name = "Chicken Burger",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis faucibus imperdiet odio, in semper risus faucibus ut. Praesent lacinia augue quis lacus posuere dictum. Morbi placerat libero felis, id gravida velit cursus ut. Quisque tempus bibendum urna, non maximus justo.",
            imageUrl = "https://www.hungryjacks.com.au/Upload/HJ/Media/UNO/HJ00571_WEB_Jack%CE%93COs-Fried-Chicken-classic_800X600_3.png",
            price = 3.0,
            stock = 100,
            categories = categories
        ),
        Product(
            id = "3",
            name = "Bacon burger",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis faucibus imperdiet odio, in semper risus faucibus ut. Praesent lacinia augue quis lacus posuere dictum. Morbi placerat libero felis, id gravida velit cursus ut. Quisque tempus bibendum urna, non maximus justo.",
            imageUrl = "https://burgerkingec.com/wp-content/uploads/2020/04/bbq-bacon-king-img.png",
            price = 3.50,
            stock = 100,
            categories = categories
        ),
        Product(
            id = "1",
            name = "Triple Meat Extra Cheese",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis faucibus imperdiet odio, in semper risus faucibus ut. Praesent lacinia augue quis lacus posuere dictum. Morbi placerat libero felis, id gravida velit cursus ut. Quisque tempus bibendum urna, non maximus justo.",
            imageUrl = "https://olo-images-live.imgix.net/2e/2e44fc9d4ecd447ebb10f0f49810b198.png?auto=format%2Ccompress&q=60&cs=tinysrgb&w=528&h=352&fit=fill&fm=png32&bg=transparent&s=51cb9035dab2ac756a30f53e6b51b449",
            price = 6.50,
            stock = 100,
            categories = categories
        ),
    )

    val combos: List<Combo> = listOf(
        Combo(
            id = "1",
            name = "Chicken Popcorn",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis faucibus imperdiet odio, in semper risus faucibus ut. Praesent lacinia augue quis lacus posuere dictum. Morbi placerat libero felis, id gravida velit cursus ut. Quisque tempus bibendum urna, non maximus justo.",
            imageUrl = "https://images.getduna.com/2bb258f4-051d-4a46-9321-b0a7a857cd71/9e9dea90ac64f3a4_domicilio_54927_744x744_1675371715.png?d=300x300&format=webp",
            price = 5.35,
            products = products
        ),
        Combo(
            id = "2",
            name = "Dynamite Burger Combo",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis faucibus imperdiet odio, in semper risus faucibus ut. Praesent lacinia augue quis lacus posuere dictum. Morbi placerat libero felis, id gravida velit cursus ut. Quisque tempus bibendum urna, non maximus justo.",
            imageUrl = "https://kfcprodnecmsimage.azureedge.net/cmsimages/uae/imagestemp/900537.png",
            price = 6,
            products = products
        ), Combo(
            id = "3",
            name = "Chicken wings",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis faucibus imperdiet odio, in semper risus faucibus ut. Praesent lacinia augue quis lacus posuere dictum. Morbi placerat libero felis, id gravida velit cursus ut. Quisque tempus bibendum urna, non maximus justo.",
            imageUrl = "https://images.getduna.com/2bb258f4-051d-4a46-9321-b0a7a857cd71/9e9dea90ac64f3a4_domicilio_499_744x744.png?d=300x300&format=webp",
            price = 4,
            products = products
        )
    )
}