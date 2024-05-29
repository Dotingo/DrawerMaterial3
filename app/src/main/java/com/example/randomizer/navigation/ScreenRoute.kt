package com.example.randomizer.navigation

sealed class ScreenRouteType(val route: String) {
    data object Main : ScreenRouteType(route = "main_screen"){
        data object Number : ScreenRouteType(route = "random_number_screen")
        data object Name : ScreenRouteType(route = "random_name_screen")
        data object Coin : ScreenRouteType(route = "random_coin_screen")
        data object List : ScreenRouteType(route = "random_list_screen")
    }
    data object Settings : ScreenRouteType(route = "settings_screen")
}