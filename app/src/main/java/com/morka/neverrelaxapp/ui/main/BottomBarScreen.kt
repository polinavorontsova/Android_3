package com.morka.neverrelaxapp.ui.main

import com.morka.neverrelaxapp.R

sealed class BottomBarScreen(val route: String, val icon: Int) {
    object Home : BottomBarScreen(
        route = "home_screen",
        icon = R.drawable.ic_home_24
    )

    object Profile : BottomBarScreen(
        route = "profile_screen",
        icon = R.drawable.ic_profile_24
    )
}