package com.morka.neverrelaxapp.ui.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.morka.neverrelaxapp.R
import com.morka.neverrelaxapp.navigation.Screen

@Composable
fun MenuScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2D3839))
            .padding(16.dp)
    ) {
        Text(
            "Menu",
            fontSize = 34.sp,
            color = Color.White,
            modifier = Modifier.padding(vertical = 10.dp)
        )

        TextButton(
            onClick = { navController.navigate(Screen.AboutDeveloper.route) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_info),
                contentDescription = "background",
                modifier = Modifier.padding(end = 10.dp)
            )
            Text("About developer", fontSize = 18.sp, color = Color.White)
            Spacer(modifier = Modifier.weight(1f))
        }

        TextButton(
            onClick = { navController.navigate(Screen.Guide.route) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_guide),
                contentDescription = "background",
                modifier = Modifier.padding(end = 10.dp)
            )
            Text("Guide", fontSize = 18.sp, color = Color.White)
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}