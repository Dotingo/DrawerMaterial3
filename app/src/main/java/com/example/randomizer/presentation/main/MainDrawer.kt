package com.example.randomizer.presentation.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
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
import com.example.randomizer.R
import com.example.randomizer.navigation.DrawerItem
import com.example.randomizer.presentation.screens.coins.RandomCoinScreen
import com.example.randomizer.presentation.screens.lists.RandomListScreen
import com.example.randomizer.presentation.screens.names.RandomNameScreen
import com.example.randomizer.presentation.screens.numbers.RandomNumber
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainDrawer(
    navigateToSettingsScreen: () -> Unit,
    viewModel: MainViewModel
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val drawerItems = listOf(
        DrawerItem(
            stringResource(R.string.number_rand),
            ImageVector.vectorResource(id = R.drawable.ic_123),
            "random_num"
        ),
        DrawerItem(
            stringResource(R.string.name_rand),
            ImageVector.vectorResource(id = R.drawable.ic_name),
            "random_name"
        ),
        DrawerItem(
            stringResource(R.string.coin_rand),
            ImageVector.vectorResource(id = R.drawable.ic_coin),
            "random_coin"
        ),
        DrawerItem(
            stringResource(R.string.list_rand),
            ImageVector.vectorResource(id = R.drawable.ic_list),
            "random_list"
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
                                    viewModel.setCurrentRandomizerScreen(item.route)
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
            },
                content = { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                    when (viewModel.currentRandomizerScreen.collectAsState().value) {
                        "random_num" -> {
                            RandomNumber(innerPadding)
                        }
                        "random_name" -> {
                            RandomNameScreen(innerPadding)
                        }
                        "random_coin" -> {
                            RandomCoinScreen(innerPadding)
                        }
                        "random_list" -> {
                            RandomListScreen(innerPadding)
                        }
                    }
                }
            )
        }
    }
}