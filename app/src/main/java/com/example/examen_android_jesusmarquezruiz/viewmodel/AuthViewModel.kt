package com.example.examen_android_jesusmarquezruiz.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class AuthViewModel : ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth

    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var confirmPassword by mutableStateOf("")

    var currentUser by mutableStateOf(auth.currentUser)
        private set

    // Login
    fun login(onLoginOk: () -> Unit, onError: () -> Unit) {
        if (email.isBlank() || password.isBlank()) {
            onError()
            return
        }
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                currentUser = auth.currentUser
                onLoginOk()
            }
            .addOnFailureListener {
                onError()
            }
    }

    fun logout(onSuccess: () -> Unit) {
        auth.signOut()
        currentUser = null
        email = ""
        password = ""
        confirmPassword = ""
        onSuccess()
    }
}
