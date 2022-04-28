package com.morka.neverrelaxapp.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.morka.neverrelaxapp.ui.about.AboutScreen
import com.morka.neverrelaxapp.ui.guide.GuideScreen
import com.morka.neverrelaxapp.ui.login.LoginScreen
import com.morka.neverrelaxapp.ui.main.MainScreen
import com.morka.neverrelaxapp.ui.menu.MenuScreen
import com.morka.neverrelaxapp.ui.registration.RegistrationScreen
import com.morka.neverrelaxapp.ui.splash.SplashScreen

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun GeneralNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(Screen.Registration.route) {
            RegistrationScreen(navController = navController)
        }
        composable(Screen.Main.route) {
            MainScreen(navController = navController)
        }
        composable(Screen.Menu.route) {
            MenuScreen(navController = navController)
        }
        composable(Screen.AboutDeveloper.route) {
            AboutScreen(navController = navController)
        }
        composable(Screen.Guide.route) {
            GuideScreen(navController = navController)
        }
    }
}