package com.morka.neverrelaxapp.ui.login

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.morka.neverrelaxapp.R
import com.morka.neverrelaxapp.navigation.Screen
import com.morka.neverrelaxapp.util.LoadingState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavHostController, viewModel: LoginScreenViewModel = viewModel()) {
    val isLoadingState = remember { mutableStateOf(false) }
    val context = LocalContext.current
    trackChanges(rememberCoroutineScope(), viewModel, isLoadingState, context, navController)
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF283234))
    ) {
        Image(
            painter = painterResource(R.drawable.leaf),
            contentDescription = "leaf",
            alignment = Alignment.BottomEnd,
            contentScale = ContentScale.None,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 100.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_logo),
                contentDescription = "logo",
                alignment = Alignment.BottomEnd,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(100.dp)
            )
            Text(
                modifier = Modifier.padding(horizontal = 25.dp),
                text = "Sign in",
                color = Color.White,
                fontSize = 36.sp
            )
            Spacer(modifier = Modifier.height(50.dp))
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .padding(horizontal = 25.dp),
                value = email,
                onValueChange = { email = it },
                label = { Text("Email", color = MaterialTheme.colors.primary) },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    textColor = Color.White,
                    cursorColor = Color.White
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .padding(horizontal = 25.dp),
                value = password,
                onValueChange = { password = it },
                label = { Text("Password", color = MaterialTheme.colors.primary) },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    textColor = Color.White,
                    cursorColor = Color.White
                ),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp)
                    .padding(top = 40.dp)
                    .alpha(0.8f),
                onClick = {
                    if (isFieldsCorrect(
                            email = email,
                            password = password
                        )
                    ) {
                        viewModel.signInWithEmailAndPassword(email, password)
                    } else {
                        Toast.makeText(
                            context,
                            "Incorrect fields data",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            ) {
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = "Sign in",
                    color = Color.White,
                    fontSize = 22.sp
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Don't have an account?",
                    color = Color.White,
                    fontSize = 16.sp
                )
                TextButton(
                    onClick = {
                        navController.popBackStack()
                        navController.navigate(Screen.Registration.route)
                    }
                )
                {
                    Text(
                        text = "Sign up",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                    )
                }
            }
        }
        if (isLoadingState.value) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.size(60.dp))
            }
        }
    }
}

private fun trackChanges(
    scope: CoroutineScope,
    viewModel: LoginScreenViewModel,
    isLoading: MutableState<Boolean>,
    context: Context,
    navController: NavHostController,
) {
    scope.launch {
        viewModel.loadingState.collect {
            scope.ensureActive()
            when (it) {
                LoadingState.IDLE -> {}
                LoadingState.LOADING -> isLoading.value = true
                LoadingState.LOADED -> {
                    navController.popBackStack()
                    navController.navigate(Screen.Main.route)
                    viewModel.reset()
                    scope.cancel()
                }
                else -> {
                    Toast.makeText(
                        context,
                        it.msg,
                        Toast.LENGTH_SHORT
                    ).show()
                    isLoading.value = false
                    viewModel.reset()
                }
            }
        }
    }
}

private fun isFieldsCorrect(email: String, password: String) =
    email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.isNotEmpty()