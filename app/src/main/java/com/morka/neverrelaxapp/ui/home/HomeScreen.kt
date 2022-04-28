package com.morka.neverrelaxapp.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.morka.neverrelaxapp.R
import com.morka.neverrelaxapp.navigation.Screen

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun HomeScreen(navController: NavHostController, homeViewModel: HomeViewModel = viewModel()) {
    val username = Firebase.auth.currentUser?.displayName ?: "User"
    val horoscopeRecommendationState = homeViewModel.horoscopeRecommendationState.collectAsState()
    val moodsRecommendationState = homeViewModel.moodRecommendationsState.collectAsState()
    val moodsState = homeViewModel.moodsState.collectAsState()

    LaunchedEffect(Unit) {
        homeViewModel.fetchHoroscope()
    }

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
            Image(
                painter = painterResource(R.drawable.background),
                contentDescription = "avatar",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(43.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color.Gray, CircleShape)
            )
        }
        Text(
            text = "Welcome back, $username!",
            fontSize = 32.sp,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
        )
        Text(
            text = "How do you feel yourself today?",
            fontSize = 20.sp,
            color = Color.Gray,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
        )
        LazyRow(modifier = Modifier.padding(vertical = 10.dp)) {
            moodsState.value.forEach { mood ->
                item(mood.title) {
                    MoodItem(moodInfo = mood, moodClicked = {
                        if (!it.isSelected) {
                            homeViewModel.activateMood(it)
                            homeViewModel.fetchMood(it.mood.synonym)
                        }
                    })
                }
            }
        }

        LazyColumn(modifier = Modifier.padding(bottom = 60.dp)) {
            when (horoscopeRecommendationState.value) {
                is HoroscopeRecommendationState.Empty -> {}
                is HoroscopeRecommendationState.Loading -> item("horoscope") {
                    CircularProgressIndicator(
                        modifier = Modifier.size(50.dp)
                    )
                }
                is HoroscopeRecommendationState.Loaded -> item("horoscope") {
                    ExpandableItem(
                        SuggestionInfo(
                            "Horoscope Suggestion",
                            (horoscopeRecommendationState.value as HoroscopeRecommendationState.Loaded).data.mood,
                            (horoscopeRecommendationState.value as HoroscopeRecommendationState.Loaded).data.description,
                            R.drawable.daily_recommendation
                        )
                    )
                }
                is HoroscopeRecommendationState.Error -> item("horoscope") {
                    Text((horoscopeRecommendationState.value as HoroscopeRecommendationState.Error).message)
                }
            }
            when (moodsRecommendationState.value) {
                is MoodRecommendationsState.Empty -> {}
                is MoodRecommendationsState.Loading -> item("moods") {
                    CircularProgressIndicator(
                        modifier = Modifier.size(50.dp)
                    )
                }
                is MoodRecommendationsState.Loaded -> (moodsRecommendationState.value as MoodRecommendationsState.Loaded).data.forEach {
                    item(it.advice) {
                        ExpandableItem(
                            SuggestionInfo(
                                "Advice",
                                "Based on your mood",
                                it.advice,
                                R.drawable.current_recommendation
                            )
                        )
                    }
                }
                is MoodRecommendationsState.Error -> item("moods") {
                    Text((moodsRecommendationState.value as MoodRecommendationsState.Error).message)
                }
            }
        }
    }
}
