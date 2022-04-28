package com.morka.neverrelaxapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.morka.neverrelaxapp.navigation.GeneralNavGraph
import com.morka.neverrelaxapp.ui.theme.NeverRelaxAppTheme

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NeverRelaxAppTheme {
                navController = rememberNavController()
                GeneralNavGraph(navController = navController)
            }
        }
    }
}