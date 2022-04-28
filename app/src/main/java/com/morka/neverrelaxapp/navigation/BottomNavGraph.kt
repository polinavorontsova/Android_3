package com.morka.neverrelaxapp.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.morka.neverrelaxapp.ui.home.HomeScreen
import com.morka.neverrelaxapp.ui.main.BottomBarScreen
import com.morka.neverrelaxapp.ui.profile.ProfileScreen

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun BottomNavGraph(bottomNavController: NavHostController, navController: NavHostController) {
    NavHost(navController = bottomNavController, startDestination = BottomBarScreen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Screen.Profile.route) {
            ProfileScreen(navController = navController)
        }
    }
}