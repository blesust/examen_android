package com.example.examen_android_jesusmarquezruiz.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.examen_android_jesusmarquezruiz.model.Jugadores
import com.example.examen_android_jesusmarquezruiz.ui.theme.ButtonBlue
import com.example.examen_android_jesusmarquezruiz.ui.theme.DetailBackground

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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(DetailBackground),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = jugadores.imagen,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit,
                    onError = {
                        Log.e("JugadorsScreen",  "Error al cargar la imagen:")
                            {it.result.throwable.message}
                    })
            }

            }

            Column(modifier = Modifier.padding(24.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = jugadores.nombre,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "${jugadores.numero} ",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = ButtonBlue
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(
                        text = jugadores.posicion,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = jugadores.nacionalidad,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.height(32.dp))

                    Text(
                        text = jugadores.imagen,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )
                }
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center)
                    {
                        Button(
                            onClick = onBack,
                            colors = ButtonDefaults.buttonColors(containerColor = ButtonBlue),
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier.padding(bottom = 16.dp)
                        ) {
                            Text("Atrás", fontSize = 16.sp)
                        }
                    }
            }
    }
}