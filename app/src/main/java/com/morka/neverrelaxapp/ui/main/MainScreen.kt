package com.morka.neverrelaxapp.ui.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.morka.neverrelaxapp.navigation.BottomNavGraph

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun MainScreen(navController: NavHostController) {
    val bottomNavController = rememberNavController()
    Scaffold(bottomBar = { BottomBar(navController = bottomNavController) }) {
        BottomNavGraph(bottomNavController, navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(BottomBarScreen.Home, BottomBarScreen.Profile)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    BottomNavigation {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        modifier = Modifier.background(Color(0xFF283233)),
        icon = {
            Icon(
                painter = painterResource(screen.icon),
                contentDescription = "logo"
            )
        },
        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        selectedContentColor = Color(0xFFFDFDFD),
        unselectedContentColor = Color(0xFF8C9191),
    )
}
