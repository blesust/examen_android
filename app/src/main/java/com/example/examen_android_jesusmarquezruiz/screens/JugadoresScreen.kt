package com.example.examen_android_jesusmarquezruiz.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.examen_android_jesusmarquezruiz.model.Jugadores

@Composable
fun JugadoresScreen(
    jugadores: Jugadores,
    onBack: () -> Unit
) {
    Scaffold{ innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())

        ) {
            
        }
        ) {
}
}