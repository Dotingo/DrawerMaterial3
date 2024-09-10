package com.example.randomizer.presentation.screens.lists

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.randomizer.R
import com.example.randomizer.navigation.ListNavigation
import com.example.randomizer.navigation.NavigationItem
import com.example.randomizer.presentation.components.DeleteDialog
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Straight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListBars(
    navController: NavHostController, id: Int, onBack: () -> Unit,
    listViewModel: ListViewModel = hiltViewModel()
) {
    var openDialog by remember {
        mutableStateOf(false)
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val items = listOf(
        NavigationItem.Pick,
        NavigationItem.Shuffle,
        NavigationItem.Group
    )

    val selectedIndex = items.indexOfFirst { it.route == currentRoute }.takeIf { it != -1 } ?: 0


    LaunchedEffect(key1 = id, key2 = navBackStackEntry) {
        if (currentRoute != "edit_screen") {
            listViewModel.getListById(id)
        }
    }

    val listItems by remember {
        derivedStateOf {
            if (listViewModel.list.items.isNotEmpty()) {
                listViewModel.listFromJson(listViewModel.list.items)
            } else {
                emptyList()
            }
        }
    }

    Scaffold(topBar = {
        if (currentRoute != "edit_screen") {
            TopAppBar(
                title = {
                    Text(
                        text = listViewModel.list.name,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        onBack()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("edit_screen")
                    }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_edit),
                            contentDescription = "edit"
                        )
                    }
                    IconButton(
                        onClick = {
                            openDialog = true
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_trash),
                            contentDescription = "delete"
                        )
                    }
                }
            )
        }
    },
        bottomBar = {
            if (currentRoute != "edit_screen") {
                AnimatedNavigationBar(
                    barColor = MaterialTheme.colorScheme.secondaryContainer,
                    ballColor = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.navigationBarsPadding(),
                    selectedIndex = selectedIndex,
                    ballAnimation = Straight(tween(300))
                ) {
                    items.forEach { item ->
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            IconButton(onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(0)
                                }
                            }) {
                                Icon(
                                    painter = painterResource(item.icon),
                                    contentDescription = "Randomizer icon"
                                )
                            }
                            Text(
                                text = stringResource(item.label),
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1
                            )
                        }
                    }
                }
            }
        }) { paddingValues ->
        DeleteDialog(
            openDialog = openDialog,
            onClose = { openDialog = false },
            onBack = onBack,
            listViewModel = listViewModel
        )

        ListNavigation(navController, paddingValues, listItems, onBack, listViewModel)
    }
}
