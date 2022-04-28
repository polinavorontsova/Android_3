package com.morka.neverrelaxapp.ui.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.morka.neverrelaxapp.R
import com.morka.neverrelaxapp.navigation.Screen
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    val auth = FirebaseAuth.getInstance()
    var logoAnimationState by remember { mutableStateOf(false) }
    val logoAlphaAnimation = animateFloatAsState(
        targetValue = if (logoAnimationState) 1f else 0f,
        animationSpec = tween(durationMillis = 1500)
    )
    var onboardingAnimationState by remember { mutableStateOf(false) }
    val alphaOnboardAnim = animateFloatAsState(
        targetValue = if (onboardingAnimationState) 1f else 0f,
        animationSpec = tween(durationMillis = 1000)
    )

    LaunchedEffect(key1 = true) {
        val client = async { auth.currentUser }
        delay(1000)
        val result = client.await()
        if (result != null) {
            navController.popBackStack()
            navController.navigate(Screen.Main.route)
        } else {
            logoAnimationState = true
            delay(500)
            onboardingAnimationState = true
        }
    }
    Splash(alpha = logoAlphaAnimation.value)
    Onboarding(
        alpha = alphaOnboardAnim.value,
        onClickLogin = {
            navController.popBackStack()
            navController.navigate(Screen.Login.route)
        },
        onClickRegister = {
            navController.popBackStack()
            navController.navigate(Screen.Registration.route)
        }
    )
}

@Composable
private fun Splash(alpha: Float) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.background),
            contentDescription = "background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        Image(
            painter = painterResource(R.drawable.ic_logo),
            contentDescription = "logo",
            alignment = Alignment.TopCenter,
            contentScale = ContentScale.None,
            modifier = Modifier
                .fillMaxSize()
                .alpha(alpha = alpha)
        )
    }
}

@Composable
private fun Onboarding(
    alpha: Float,
    onClickRegister: () -> Unit,
    onClickLogin: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .alpha(alpha = alpha),
        verticalArrangement = Arrangement.Bottom
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Hey!",
                color = Color.White, fontSize = 36.sp
            )
            Text(
                text = "Enjoy.",
                color = Color.White, fontSize = 22.sp
            )
            Text(
                text = "Be patient.",
                color = Color.White, fontSize = 22.sp
            )
            Text(
                text = "Do well.",
                color = Color.White, fontSize = 22.sp
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 100.dp)
        ) {
            Button(
                modifier = Modifier
                    .padding(25.dp)
                    .fillMaxWidth(),
                onClick = onClickLogin
            ) {
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = "Sign In",
                    color = Color.White,
                    fontSize = 22.sp
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Don't have an account?",
                    color = Color.White,
                    fontSize = 16.sp
                )
                TextButton(
                    onClick = onClickRegister
                )
                {
                    Text(
                        text = "Register",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                    )
                }
            }
        }
    }
}