package com.example.drawermaterial3

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.drawermaterial3.screens.RandomCountryScreen
import com.example.drawermaterial3.screens.RandomNameScreen
import com.example.drawermaterial3.screens.RandomNumber
import com.example.drawermaterial3.screens.RandomWordScreen
import com.example.drawermaterial3.ui.theme.DrawerMaterial3Theme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Drawer() {
    val IcAbc = ImageVector.vectorResource(id = R.drawable.ic_abc)
    val IcNum = ImageVector.vectorResource(id = R.drawable.ic_123)
    val IcCountry = ImageVector.vectorResource(id = R.drawable.ic_globe)
    val IcName = ImageVector.vectorResource(id = R.drawable.ic_name)

    val drawerItems = listOf(
        DrawerItem("Numbers", IcNum, IcNum, "random_num"),
        DrawerItem("Names", IcName, IcName, "random_name"),
        DrawerItem("Letters", IcAbc, IcAbc, "random_word"),
        DrawerItem("Countries", IcCountry, IcCountry, "random_countries")
    )

    var selectedItem by remember { mutableStateOf(drawerItems[0]) }

    DrawerMaterial3Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val scope = rememberCoroutineScope()

            ModalNavigationDrawer(
                drawerContent = {
                    ModalDrawerSheet(modifier = Modifier.width(300.dp)) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.randomizer_header),
                                contentDescription = "header"
                            )
                        }
                        drawerItems.forEach { item ->
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
                            )
                        }
                    }
                },
                drawerState = drawerState,
            ) {
                Scaffold(topBar = {

                    TopAppBar(title = {
                        Text(text = selectedItem.title)
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
                            IconButton(onClick = { }) {
                                Icon(
                                    imageVector = Icons.Default.Settings,
                                    contentDescription = "Settings"
                                )
                            }
                        })

                }, content = { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                    when (selectedItem.route) {
                        "random_num" -> {
                            RandomNumber()
                        }

                        "random_name" -> {
                            RandomNameScreen()
                        }

                        "random_word" -> {
                            RandomWordScreen()
                        }

                        "random_countries" -> {
                            RandomCountryScreen()
                        }
                    }
                })
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