package com.morka.neverrelaxapp.ui.profile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.morka.neverrelaxapp.R
import com.morka.neverrelaxapp.navigation.Screen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileScreen(navController: NavHostController) {
    val username = Firebase.auth.currentUser?.displayName ?: "User"
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF000000))
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2D3839))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = { navController.navigate(Screen.Menu.route) }) {
                Icon(
                    painter = painterResource(R.drawable.ic_menu_24),
                    contentDescription = "logo",
                    tint = Color.White
                )
            }
            Icon(
                painter = painterResource(R.drawable.ic_logo),
                contentDescription = "logo",
                modifier = Modifier.size(130.dp),
                tint = Color.White
            )
            TextButton(onClick = {
                Firebase.auth.signOut()
                navController.popBackStack()
                navController.navigate(Screen.Splash.route)
            }) {
                Icon(
                    painter = painterResource(R.drawable.ic_baseline_exit_to_app_24),
                    contentDescription = "logout",
                    tint = Color.White
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.background),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color.Gray, CircleShape)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(username, fontSize = 24.sp, color = Color.White)
                Text("Age: 20", fontSize = 18.sp, color = Color.White)
                Text("Weight: 57kg", fontSize = 18.sp, color = Color.White)
            }
        }

        val data = listOf(
            "Item 1",
            "Item 2",
            "Item 3",
            "Item 4",
            "Item 5",
            "Item 1",
            "Item 2",
            "Item 3",
            "Item 4",
            "Item 5",
            "Item 1",
            "Item 2",
            "Item 3",
            "Item 4",
            "Item 5",
        )

        LazyVerticalGrid(
            modifier = Modifier.padding(bottom = 50.dp),
            cells = GridCells.Adaptive(160.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            data.forEach { item ->
                item {
                    TextButton(
                        onClick = { /**TODO*/ },
                        contentPadding = PaddingValues()
                    ) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .padding(7.dp),
                            shape = RoundedCornerShape(20.dp),
                            backgroundColor = Color.LightGray
                        ) {
                            Image(
                                painter = painterResource(R.drawable.background),
                                contentDescription = "photo",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                            Text(
                                text = item,
                                fontSize = 16.sp,
                                textAlign = TextAlign.Left,
                                color = Color.White,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(start = 10.dp, top = 90.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}