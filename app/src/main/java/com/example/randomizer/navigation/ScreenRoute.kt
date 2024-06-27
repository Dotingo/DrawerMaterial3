package com.example.randomizer.navigation

sealed class ScreenRouteType(val route: String) {
    data object Main : ScreenRouteType(route = "main_screen") {
        data object Number : ScreenRouteType(route = "random_number_screen")
        data object Name : ScreenRouteType(route = "random_name_screen")
        data object Coin : ScreenRouteType(route = "random_coin_screen")
        data object ListScreen : ScreenRouteType(route = "random_list_screen") {
            data object List : ScreenRouteType("list") {
                data object CreateList : ScreenRouteType("create_list")
                data object InsideList : ScreenRouteType("inside_list")
            }
        }
    }

    data object Settings : ScreenRouteType(route = "settings_screen")
}

