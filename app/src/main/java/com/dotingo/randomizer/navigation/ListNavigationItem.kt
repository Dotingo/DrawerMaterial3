package com.dotingo.randomizer.navigation

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.dotingo.randomizer.R
import com.dotingo.randomizer.presentation.ui.icons.list_navigate_icons.FilledGroupIcon
import com.dotingo.randomizer.presentation.ui.icons.list_navigate_icons.FilledPickIcon
import com.dotingo.randomizer.presentation.ui.icons.list_navigate_icons.OutlinedGroupIcon
import com.dotingo.randomizer.presentation.ui.icons.list_navigate_icons.OutlinedPickIcon
import com.dotingo.randomizer.presentation.ui.icons.list_navigate_icons.ShuffleIcon

sealed class NavigationItem(
    val route: String,
    val outlinedIcon: ImageVector,
    val filledIcon: ImageVector,
    @StringRes val label: Int,
) {

    data object Pick : NavigationItem("pick", OutlinedPickIcon, FilledPickIcon, R.string.pick)

    data object Shuffle : NavigationItem("shuffle", ShuffleIcon, ShuffleIcon, R.string.shuffle)

    data object Group : NavigationItem("group", OutlinedGroupIcon, FilledGroupIcon, R.string.groups)

}