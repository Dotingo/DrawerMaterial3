package com.example.randomizer.data.type

sealed class ScreenRouteType(val route: String) {
    data object Main : ScreenRouteType(route = "main_screen")
    data object Settings : ScreenRouteType(route = "settings_screen")
}