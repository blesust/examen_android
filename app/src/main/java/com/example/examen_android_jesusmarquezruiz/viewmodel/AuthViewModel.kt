package com.example.examen_android_jesusmarquezruiz.viewmodel

class AuthViewModel : ViewModel() {
    private val auth : FirebaseAuth = Firebase.auth

    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var confirmPassword by mutableStateOf("")

    var currentUser by mutableStateOf(auth.currentUser)
        private set

    //Login
    fun login(onLoginOk: () -> Unit, onError: () -> Unit){
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                currentUser = auth.currentUser
                onLoginOk()
            }
            .addOnFailureListener {
                onError()
            }

    }

    fun logout(onSuccess: () -> Unit){
        auth.signOut()
        currentUser = null
        email = ""
        password = ""
        confirmPassword = ""
        onSuccess()

    }


}