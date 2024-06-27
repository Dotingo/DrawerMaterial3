package com.example.randomizer.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.randomizer.presentation.screens.coins.RandomCoinScreen
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
                    navigateToList = {
                        navController.navigate(route = ScreenRouteType.Main.ListScreen.List.InsideList.route) {
                            launchSingleTop = true
                        }
                    }
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
            composable(ScreenRouteType.Main.ListScreen.List.InsideList.route) {
                val listNavController = rememberNavController()
                RandomListScreen(
                    listNavController,
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
    }
}