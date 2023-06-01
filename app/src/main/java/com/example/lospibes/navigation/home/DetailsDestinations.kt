package com.example.lospibes.navigation.home

sealed class DetailsDestinations(val route: String) {
    object ProductDetailsScreen : DetailsDestinations(route = "product_details_screen")

    /* Profile */
    object ProfileDetailsScreen : DetailsDestinations(route = "profile_details_screen")
    object OrdersScreen : DetailsDestinations(route = "orders_screen")
    object AddressesScreen : DetailsDestinations(route = "addresses_screen")
    object NewAddressScreen : DetailsDestinations(route = "new_address_screen")
    object AddressDetailsScreen : DetailsDestinations(route = "address_detail_screen")
}
