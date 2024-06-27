package com.example.randomizer.presentation.screens.lists

import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.randomizer.R
import com.example.randomizer.navigation.NavigationItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RandomListScreen(navController: NavHostController, onBack: () -> Unit) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = "List name",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            navigationIcon = {
                IconButton(onClick = {
                    onBack()
                }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                        contentDescription = null
                    )
                }
            },
            actions = {
                IconButton(onClick = {

                }
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_edit),
                        contentDescription = "apply"
                    )
                }
                IconButton(onClick = {

                }
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_trash),
                        contentDescription = "apply"
                    )
                }
            }
        )
    },
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                val items =
                    listOf(
                        NavigationItem.Pick,
                        NavigationItem.Shuffle,
                        NavigationItem.Group
                    )
                items.forEach { item ->
                    val selected = currentRoute == item.route
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navController.navigate(item.route)
                            Log.d("my",item.route)
                        },
                        icon = {
                            if (!selected) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(item.icon),
                                    contentDescription = "pick item"
                                )
                            } else {
                                Icon(
                                    imageVector = ImageVector.vectorResource(item.filledIcon),
                                    contentDescription = "pick item"
                                )
                            }
                        },
                        label = { Text(text = stringResource(item.label)) })
                }

            }
        }) { paddingValues ->
        NavHost(navController, startDestination = NavigationItem.Pick.route) {
            composable(NavigationItem.Pick.route) {
                PickListItemsScreen(paddingValues) }
            composable(NavigationItem.Shuffle.route) {
                ShuffleListItemsScreen(paddingValues) }
            composable(NavigationItem.Group.route) {
                GroupListItemsScreen(paddingValues) }
        }
    }
}
