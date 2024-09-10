package com.example.randomizer.presentation.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.randomizer.R
import com.example.randomizer.navigation.DrawerItem
import com.example.randomizer.navigation.DrawerNavHost
import com.example.randomizer.navigation.ScreenRouteType
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainDrawer(
    navigateToSettingsScreen: () -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var gesturesEnabled by remember {
        mutableStateOf(true)
    }
    val scope = rememberCoroutineScope()
    val drawerItems = listOf(
        DrawerItem(
            stringResource(R.string.number_rand),
            ImageVector.vectorResource(id = R.drawable.ic_123),
            ScreenRouteType.Main.Number.route
        ),
        DrawerItem(
            stringResource(R.string.name_rand),
            ImageVector.vectorResource(id = R.drawable.ic_name),
            ScreenRouteType.Main.Name.route
        ),
        DrawerItem(
            stringResource(R.string.coin_rand),
            ImageVector.vectorResource(id = R.drawable.ic_coin),
            ScreenRouteType.Main.Coin.route
        ),
        DrawerItem(
            stringResource(R.string.list_rand),
            ImageVector.vectorResource(id = R.drawable.ic_list),
            ScreenRouteType.Main.ListScreen.route
        ),
        DrawerItem(
            stringResource(R.string.country_rand),
            ImageVector.vectorResource(id = R.drawable.ic_globe),
            ScreenRouteType.Main.Country.route
        ),
        DrawerItem(
            stringResource(R.string.color_rand),
            ImageVector.vectorResource(id = R.drawable.ic_color_swatch),
            ScreenRouteType.Main.Color.route
        ),
        DrawerItem(
            stringResource(R.string.dice_rand),
            ImageVector.vectorResource(id = R.drawable.ic_dice),
            ScreenRouteType.Main.Dice.route
        ),
        DrawerItem(
            stringResource(R.string.musical_genres_rand),
            ImageVector.vectorResource(id = R.drawable.ic_music_record),
            ScreenRouteType.Main.MusGenres.route
        )
    )
    val currentRandomizerScreen = viewModel.currentRandomizerScreen.collectAsState()
    var selectedItem by remember {
        mutableStateOf(drawerItems.find { it.route == currentRandomizerScreen.value }
            ?: drawerItems[0])
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        ModalNavigationDrawer(
            gesturesEnabled = gesturesEnabled,
            drawerContent = {
                ModalDrawerSheet(
                    drawerContainerColor = MaterialTheme.colorScheme.background,
                    modifier = Modifier.width(300.dp)
                ) {
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
                                    viewModel.setCurrentRandomizerScreen(item.route)
                                    navController.navigate(item.route) {
                                        popUpTo(0) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                    scope.launch {
                                        drawerState.close()
                                    }
                                },
                                icon = {
                                    Icon(
                                        imageVector = item.icon,
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

            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            Scaffold(topBar = {
                if (
                    currentRoute != ScreenRouteType.Main.ListScreen.List.CreateList.route &&
                    currentRoute != "${ScreenRouteType.Main.ListScreen.List.InsideList.route}/{id}"
                ) {
                    gesturesEnabled = true
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
                                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_menu),
                                    contentDescription = "Menu"
                                )
                            }
                        },
                        actions = {
                            IconButton(
                                onClick = navigateToSettingsScreen
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_settings),
                                    contentDescription = "Settings"
                                )
                            }
                        }
                    )
                } else {
                    gesturesEnabled = false
                }
            },
                content = { innerPadding ->
                    DrawerNavHost(navController = navController, innerPadding = innerPadding)
                }
            )
        }
    }
}