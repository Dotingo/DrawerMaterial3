package com.example.randomizer.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.randomizer.presentation.screens.lists.GroupListItemsScreen
import com.example.randomizer.presentation.screens.lists.PickListItemsScreen
import com.example.randomizer.presentation.screens.lists.ShuffleListItemsScreen

@Composable
fun ListNavigation(
    navController: NavHostController,
    pickScreen: @Composable () -> Unit,
    shuffleScreen: @Composable () -> Unit,
    groupScreen: @Composable () -> Unit
) {
    NavHost(navController, startDestination = NavigationItem.Pick.route) {
        composable(NavigationItem.Pick.route) { pickScreen() }
        composable(NavigationItem.Shuffle.route) { shuffleScreen() }
        composable(NavigationItem.Group.route) { groupScreen() }
    }
}