package com.example.examen_android_jesusmarquezruiz.screens

/*HomeScreen*/
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.examen_android_jesusmarquezruiz.model.Jugadores
import com.example.examen_android_jesusmarquezruiz.ui.theme.ButtonBlue
import com.example.examen_android_jesusmarquezruiz.viewmodel.AuthViewModel
import com.example.examen_android_jesusmarquezruiz.viewmodel.ProductViewModel

@Composable
fun HomeScreen(
    authViewModel: AuthViewModel,
    jugadoresViewModel: ProductViewModel,
    onViewProduct: (Jugadores) -> Unit,
    onNavigateToConfirm: (String, String?) -> Unit,
    onLogout: () -> Unit
) {
    val jugadores by productViewModel.jugadores.collectAsState()

    Scaffold { innerPadding ->
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
                Text(
                    text = "Bienvenido ${authViewModel.currentUser?.email ?: ""}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
                IconButton(onClick = { authViewModel.logout(onLogout) }, modifier = Modifier.size(32.dp)) {
                    Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = "Cerrar sesión", modifier = Modifier.size(20.dp))
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedTextField(
                            value = productViewModel.name,
                            onValueChange = { productViewModel.name = it },
                            placeholder = { Text("Nombre", fontSize = 14.sp) },
                            modifier = Modifier.weight(1.5f),
                            singleLine = true,
                            shape = RoundedCornerShape(8.dp),
                            colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color.White, focusedContainerColor = Color.White)
                        )
                        OutlinedTextField(
                            value = productViewModel.numero,
                            onValueChange = { productViewModel.numero = it },
                            placeholder = { Text("Numero", fontSize = 14.sp) },
                            modifier = Modifier.weight(1f),
                            singleLine = true,
                            shape = RoundedCornerShape(8.dp),
                            colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color.White, focusedContainerColor = Color.White)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = productViewModel.Posicion,
                        onValueChange = { productViewModel.posicion = it },
                        placeholder = { Text("Posicion", fontSize = 14.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 2,
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color.White, focusedContainerColor = Color.White)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = productViewModel.nacionalidad,
                        onValueChange = { productViewModel.nacionalidad = it },
                        placeholder = { Text("Nacionalidad", fontSize = 14.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color.White, focusedContainerColor = Color.White)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = productViewModel.imagen,
                        onValueChange = { productViewModel.imagen = it },
                        placeholder = { Text("URL imagen", fontSize = 14.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color.White, focusedContainerColor = Color.White)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = {
                            val tipo = if (productViewModel.editingJugadores == null) "agregar" else "editar"
                            onNavigateToConfirm(tipo, null)
                        },
                        modifier = Modifier.fillMaxWidth().height(44.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = ButtonBlue),
                        shape = RoundedCornerShape(22.dp)
                    ) {
                        Text(if (jugadoresViewModel.editingJugadores == null) "Agregar Jugador" else "Cancelar", fontSize = 14.sp)
                    }

                    if (jugadoresViewModel.editingJugadores != null) {
                        TextButton(
                            onClick = { jugadoresViewModel.clearFields() },
                            modifier = Modifier.align(Alignment.CenterHorizontally).height(32.dp)
                        ) {
                            Text("Cancelar", color = Color.Red, fontSize = 12.sp)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Plantilla temporada 25/26", fontWeight = FontWeight.Bold, fontSize = 18.sp, modifier = Modifier.padding(bottom = 8.dp))

            LazyColumn(
                modifier = Modifier.fillMaxWidth().weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(items = jugadores) { jug ->
                    ProductListItem(
                        jugadores = jug,
                        onView = { onViewProduct(jug) },
                        onEdit = { jugadoresViewModel.startEditing(jug) },
                        onDelete = { onNavigateToConfirm("eliminar", jug.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun ProductListItem(
    jugadores: Jugadores,
    onView: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = jugadores.nombre,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = Color.Black
                )
                Text(
                    text = "${jugadores.numero}",
                    color = Color.Gray,
                    fontSize = 13.sp
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onView, modifier = Modifier.size(36.dp)) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Ver", tint = Color.Black, modifier = Modifier.size(20.dp))
                }
                IconButton(onClick = onEdit, modifier = Modifier.size(36.dp)) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Editar", tint = Color.Black, modifier = Modifier.size(20.dp))
                }
                IconButton(onClick = onDelete, modifier = Modifier.size(36.dp)) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Eliminar", tint = Color(0xFFD32F2F), modifier = Modifier.size(20.dp))
                }
            }
        }
    }
}
