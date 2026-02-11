package com.example.examen_android_jesusmarquezruiz.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.examen_android_jesusmarquezruiz.model.Jugadores
import com.example.examen_android_jesusmarquezruiz.viewmodel.AuthViewModel
import com.example.examen_android_jesusmarquezruiz.viewmodel.ProductViewModel

@Composable
fun HomeScreen(
    authViewModel: AuthViewModel,
    jugadoresViewModel: ProductViewModel,
    onViewProduct: (Jugadores) -> Unit,
    onLogout: () -> Unit
) {
    val jugadores by jugadoresViewModel.jugadores.collectAsState()
    var showForm by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = Color(0xFFF1F8E9),
        floatingActionButton = {
            if (!showForm) {
                FloatingActionButton(
                    onClick = { 
                        jugadoresViewModel.clearFields()
                        showForm = true 
                    },
                    containerColor = Color(0xFF2E7D32),
                    contentColor = Color.White
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Agregar Jugador")
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val email = authViewModel.currentUser?.email ?: ""
                Text(
                    text = "Bienvenido $email",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF2E7D32)
                )
                IconButton(onClick = { authViewModel.logout(onLogout) }) {
                    Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = "Cerrar sesión", tint = Color(0xFF2E7D32))
                }
            }

            if (showForm) {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(4.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Nuevo Jugador",
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF2E7D32),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        OutlinedTextField(
                            value = jugadoresViewModel.name,
                            onValueChange = { jugadoresViewModel.name = it },
                            label = { Text("Nombre") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = jugadoresViewModel.numero,
                            onValueChange = { jugadoresViewModel.numero = it },
                            label = { Text("Número") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = jugadoresViewModel.posicion,
                            onValueChange = { jugadoresViewModel.posicion = it },
                            label = { Text("Posición") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = jugadoresViewModel.nacionalidad,
                            onValueChange = { jugadoresViewModel.nacionalidad = it },
                            label = { Text("Nacionalidad") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = jugadoresViewModel.imagen,
                            onValueChange = { jugadoresViewModel.imagen = it },
                            label = { Text("URL Imagen") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Button(
                                onClick = {
                                    jugadoresViewModel.addJugador(
                                        jugadoresViewModel.name,
                                        jugadoresViewModel.numero,
                                        jugadoresViewModel.nacionalidad,
                                        jugadoresViewModel.posicion,
                                        jugadoresViewModel.imagen
                                    )
                                    showForm = false
                                },
                                modifier = Modifier.weight(1f),
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32))
                            ) {
                                Text("Agregar")
                            }
                            OutlinedButton(
                                onClick = { 
                                    showForm = false
                                    jugadoresViewModel.clearFields()
                                },
                                modifier = Modifier.weight(1f),
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red)
                            ) {
                                Text("Cancelar")
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Plantilla Unicaja Baloncesto",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color(0xFF1B5E20),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            LazyColumn(
                modifier = Modifier.fillMaxWidth().weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 80.dp)
            ) {
                items(items = jugadores) { jug ->
                    JugadorListItem(
                        jugador = jug,
                        onView = { onViewProduct(jug) },
                        onDelete = { jugadoresViewModel.deleteJugador(jug.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun JugadorListItem(
    jugador: Jugadores,
    onView: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = jugador.imagen,
                contentDescription = jugador.nombre,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = jugador.nombre,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Text(
                    text = "Número: ${jugador.numero} | ${jugador.posicion}",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }

            Row {
                IconButton(onClick = onView) {
                    Icon(Icons.Default.Visibility, contentDescription = "Ver", tint = Color(0xFF2E7D32))
                }
                IconButton(onClick = onDelete) {
                    Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = Color.Red)
                }
            }
        }
    }
}
