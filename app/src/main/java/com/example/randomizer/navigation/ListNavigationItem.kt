package com.example.randomizer.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.randomizer.R

sealed class NavigationItem(
    val route: String,
    @DrawableRes val icon: Int,
    @DrawableRes val filledIcon: Int,
    @StringRes val label: Int,
) {

    data object Pick : NavigationItem("pick", R.drawable.ic_pick, R.drawable.ic_pick_filled, R.string.pick)

    data object Shuffle : NavigationItem("shuffle", R.drawable.ic_shuffle, R.drawable.ic_shuffle, R.string.shuffle)

    data object Group : NavigationItem("group", R.drawable.ic_group2, R.drawable.ic_group_filled, R.string.groups)

}