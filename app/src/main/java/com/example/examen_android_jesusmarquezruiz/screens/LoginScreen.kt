package com.example.examen_android_jesusmarquezruiz.screens

import android.widget.Toast
import androidx.benchmark.traceprocessor.Row
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.examen_android_jesusmarquezruiz.ui.theme.ButtonBlue
import com.example.examen_android_jesusmarquezruiz.viewmodel.AuthViewModel

@OptIn (ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: AuthViewModel,
    onNavigateToRegistro: () -> Unit,
    onLoginSuccess: () -> Unit
){
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Login") },
        }
    ) {paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Login",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            OutlinedTextField(
                value = viewModel.email,
                onValueChange = {
                    val it = null
                    viewModel.email = it
                },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = viewModel.password,
            onValueChange = {
                val it = null
                viewModel.password = it
            },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            shape = RoundedCornerShape(8.dp)
        )

        Spacer (modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    viewModel.login(
                        onLoginOk = onLoginSuccess,
                        onError = { Toast.makeText(context, "Error al iniciar sesión",
                            Toast.LENGTH_SHORT).show() }
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ButtonBlue),
                shape = RoundedCornerShape(25.dp)
            ) {
                Text("Entrar", color = androidx.compose.ui.graphics.Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "¿No tienes cuenta? ")
                TextButton(onClick = onNavigateToRegistro) {
                    Text(text = "Regístrate", color = ButtonBlue)
                }
            }
        }
            }
}


