package com.example.randomizer.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.randomizer.presentation.screens.coins.RandomCoinScreen
import com.example.randomizer.presentation.screens.colors.RandomColorScreen
import com.example.randomizer.presentation.screens.countries.RandomCountriesScreen
import com.example.randomizer.presentation.screens.lists.CreateListScreen
import com.example.randomizer.presentation.screens.lists.MenuListScreen
import com.example.randomizer.presentation.screens.lists.RandomListScreen
import com.example.randomizer.presentation.screens.names.RandomNameScreen
import com.example.randomizer.presentation.screens.numbers.RandomNumber

@Composable
fun DrawerNavHost(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = ScreenRouteType.Main.Number.route,
        enterTransition = {
            EnterTransition.None
        },
        exitTransition = {
            ExitTransition.None
        },
        popEnterTransition = {
            EnterTransition.None
        },
        popExitTransition = {
            ExitTransition.None
        }
    ) {
        composable(ScreenRouteType.Main.Number.route) { RandomNumber(innerPadding) }
        composable(ScreenRouteType.Main.Name.route) { RandomNameScreen(innerPadding) }
        composable(ScreenRouteType.Main.Coin.route) { RandomCoinScreen(innerPadding) }
        navigation(
            startDestination = ScreenRouteType.Main.ListScreen.List.route,
            route = ScreenRouteType.Main.ListScreen.route
        ) {
            composable(ScreenRouteType.Main.ListScreen.List.route) {
                MenuListScreen(
                    navigateToCreateListScreen = {
                        navController.navigate(route = ScreenRouteType.Main.ListScreen.List.CreateList.route) {
                            launchSingleTop = true
                        }
                    },
                    navigateToList = { id ->
                        navController.navigate(route = "${ScreenRouteType.Main.ListScreen.List.InsideList.route}/$id") {
                            launchSingleTop = true
                        }
                    },
                    paddingValues = innerPadding
                )
            }
            composable(ScreenRouteType.Main.ListScreen.List.CreateList.route) {
                CreateListScreen(
                    onBack = {
                        if (navController.currentBackStackEntry?.lifecycle?.currentState
                            == Lifecycle.State.RESUMED
                        ) {
                            navController.popBackStack()
                        }
                    })
            }
            composable(
                route = "${ScreenRouteType.Main.ListScreen.List.InsideList.route}/{id}",
                arguments = listOf(navArgument("id") {
                    type = NavType.IntType
                })
            ) { navBackStackEntry ->
                val listNavController = rememberNavController()
                val id = navBackStackEntry.arguments?.getInt("id")
                RandomListScreen(
                    navController = listNavController,
                    id = id!!,
                    onBack = {
                        if (navController.currentBackStackEntry?.lifecycle?.currentState
                            == Lifecycle.State.RESUMED
                        ) {
                            navController.popBackStack()
                        }
                    }
                )
            }
        }
        composable(ScreenRouteType.Main.Country.route) { RandomCountriesScreen(paddingValues = innerPadding) }
        composable(ScreenRouteType.Main.Color.route) { RandomColorScreen(paddingValues = innerPadding) }
    }
}