package com.example.randomizer

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.randomizer.screens.RandomCountryScreen
import com.example.randomizer.screens.RandomNameScreen
import com.example.randomizer.screens.RandomNumber
import com.example.randomizer.screens.RandomCoinScreen
import com.example.randomizer.screens.SettingsScreen
import com.example.randomizer.ui.theme.RandomizerTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Drawer() {
    var isDrawerEnabled by remember { mutableStateOf(true) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    val icCoin = ImageVector.vectorResource(id = R.drawable.ic_coin)
    val icNum = ImageVector.vectorResource(id = R.drawable.ic_123)
    val icCountry = ImageVector.vectorResource(id = R.drawable.ic_globe)
    val icName = ImageVector.vectorResource(id = R.drawable.ic_name)

    val drawerItems = listOf(
        DrawerItem(stringResource(R.string.number_rand), icNum, icNum, "random_num"),
        DrawerItem(stringResource(R.string.name_rand), icName, icName, "random_name"),
        DrawerItem(stringResource(R.string.coin_rand), icCoin, icCoin, "random_coin"),
        DrawerItem(stringResource(R.string.country_rand), icCountry, icCountry, "random_countries")
    )

    var selectedItem by remember { mutableStateOf(drawerItems[0]) }

    RandomizerTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NavHost(navController = navController, startDestination = "main",
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(200)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(200)
                    )
                },
                popEnterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(200)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(200)
                    )
                }
            ) {
                composable("main") {
            ModalNavigationDrawer(
                gesturesEnabled = navController.currentBackStackEntry?.destination?.route == "main",
                drawerContent = {
                    ModalDrawerSheet(modifier = Modifier.width(300.dp)) {
                        LazyColumn(modifier = Modifier.padding(10.dp)) {
                            item {
                                Image(
                                    painter = painterResource(id = R.drawable.randomizer_header),
                                    contentDescription = "header",
                                    modifier = Modifier.clip(MaterialTheme.shapes.small)
                                )
                            }
                            items(drawerItems) { item ->
                                NavigationDrawerItem(
                                    label = { Text(text = item.title) },
                                    selected = item == selectedItem,
                                    onClick = {
                                        selectedItem = item
                                        scope.launch {
                                            drawerState.close()
                                        }
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = if (item == selectedItem) {
                                                item.selectedIcon
                                            } else item.unselectedIcon,
                                            contentDescription = item.title
                                        )
                                    },
                                    modifier = Modifier
                                        .padding(NavigationDrawerItemDefaults.ItemPadding)
                                        .padding(top = 10.dp)
                                )
                            }
                        }
                    }
                },
                drawerState = drawerState,
            ) {
                        Scaffold(topBar = {
                            TopAppBar(title = {
                                Text(
                                    text = selectedItem.title,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            },
                                navigationIcon = {
                                    IconButton(onClick = {
                                        scope.launch {
                                            drawerState.open()
                                        }
                                    }) {
                                        Icon(
                                            imageVector = Icons.Default.Menu,
                                            contentDescription = "Menu"
                                        )
                                    }
                                },
                                actions = {
                                    IconButton(onClick = {
                                        navController.navigate("settings")
                                        isDrawerEnabled = false
                                    }) {
                                        Icon(
                                            imageVector = Icons.Default.Settings,
                                            contentDescription = "Settings"
                                        )
                                    }
                                }
                            )

                        }, content = { innerPadding ->
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(innerPadding)
                            )
                            when (selectedItem.route) {
                                "random_num" -> {
                                    RandomNumber(innerPadding)
                                }

                                "random_name" -> {
                                    RandomNameScreen(innerPadding)
                                }

                                "random_coin" -> {
                                    RandomCoinScreen(innerPadding)
                                }

                                "random_countries" -> {
                                    RandomCountryScreen()
                                }
                            }
                        }
                        )
                    }

                }
                composable("settings") {
                    SettingsScreen {
                        if (navController.currentBackStackEntry?.lifecycle?.currentState
                            == Lifecycle.State.RESUMED
                        ) {
                            isDrawerEnabled = true
                            navController.popBackStack()
                        }
                    }
                }
            }
        }
    }
}


data class DrawerItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: String
)