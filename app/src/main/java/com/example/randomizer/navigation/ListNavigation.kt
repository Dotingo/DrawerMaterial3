package com.example.randomizer.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.toMutableStateList
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.randomizer.presentation.screens.lists.EditListScreen
import com.example.randomizer.presentation.screens.lists.GroupListItemsScreen
import com.example.randomizer.presentation.screens.lists.ListViewModel
import com.example.randomizer.presentation.screens.lists.PickListItemsScreen
import com.example.randomizer.presentation.screens.lists.ShuffleListItemsScreen

@Composable
fun ListNavigation(
    navController: NavHostController,
    paddingValues: PaddingValues,
    items: List<String>,
    onBack: () -> Unit,
    listViewModel: ListViewModel
) {
    NavHost(navController, startDestination = NavigationItem.Pick.route,
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
        composable(NavigationItem.Pick.route) {
            PickListItemsScreen(paddingValues, items, listViewModel)
        }
        composable(NavigationItem.Shuffle.route) {
            ShuffleListItemsScreen(paddingValues, items, listViewModel)
        }
        composable(NavigationItem.Group.route) {
            GroupListItemsScreen(paddingValues, items, listViewModel)
        }
        composable("edit_screen") {
            EditListScreen(navController, items.toMutableStateList(), onBack, listViewModel)
        }
    }
}

