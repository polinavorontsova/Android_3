package com.morka.neverrelaxapp.ui.about

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun AboutScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF283234))
            .padding(20.dp)
    ) {
        Text(
            text = "Vorontsova Polina",
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            color = Color.White
        )
        Text(
            text = "951005",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            color = Color.White
        )
        Text(
            text = "Relax App",
            fontStyle = FontStyle.Italic,
            fontSize = 28.sp,
            color = Color.White
        )
    }
}