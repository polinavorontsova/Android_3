package com.morka.neverrelaxapp.navigation

sealed class Screen(val route: String) {
    object Splash: Screen(route = "splash_screen")
    object Login: Screen(route = "login_screen")
    object Registration: Screen(route = "registration_screen")
    object Main: Screen(route = "main_screen")
    object Menu: Screen(route = "menu_screen")
    object Home : Screen(route = "home_screen")
    object Profile: Screen(route = "profile_screen")
    object AboutDeveloper : Screen(route = "about_screen")
    object Guide : Screen(route = "guide_screen")
}
