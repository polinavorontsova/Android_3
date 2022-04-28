package com.morka.neverrelaxapp.ui.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.morka.neverrelaxapp.util.LoadingState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class RegistrationScreenViewModel : ViewModel() {

    val loadingState = MutableStateFlow(LoadingState.IDLE)

    fun signup(name: String, email: String, password: String) = viewModelScope.launch {
        try {
            loadingState.emit(LoadingState.LOADING)
            val auth = Firebase.auth.createUserWithEmailAndPassword(email, password).await()
            val updateName = UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build()
            auth.user?.let {
                it.updateProfile(updateName).await()
                loadingState.emit(LoadingState.LOADED)
            } ?: loadingState.emit(LoadingState.error("User registration failed."))
        } catch (e: Exception) {
            loadingState.emit(LoadingState.error(e.localizedMessage))
        }
    }

    suspend fun reset() {
        loadingState.emit(LoadingState.IDLE)
    }
}