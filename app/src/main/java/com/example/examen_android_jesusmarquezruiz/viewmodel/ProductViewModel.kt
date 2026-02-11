package com.example.examen_android_jesusmarquezruiz.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.examen_android_jesusmarquezruiz.model.Jugadores
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProductViewModel : ViewModel() {
    private val db = Firebase.firestore
    private val jugadoresCollection = db.collection("jugadores")

    private val _jugadores = MutableStateFlow<List<Jugadores>>(emptyList())
    val jugadores: StateFlow<List<Jugadores>> = _jugadores

    var name by mutableStateOf("")
    var numero by mutableStateOf("")
    var nacionalidad by mutableStateOf("")
    var posicion by mutableStateOf("")
    var equipo by mutableStateOf("")
    var imagen by mutableStateOf("")

    init {
        getJugadores()
    }

    private fun getJugadores() {
        jugadoresCollection.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w("ProductViewModel", "Listen failed", e)
                return@addSnapshotListener
            }
            if (snapshot != null) {
                _jugadores.value = snapshot.documents.mapNotNull { doc ->
                    val jug = doc.toObject(Jugadores::class.java)
                    jug?.copy(id = doc.id)
                }
            }
        }
    }

    fun addJugador(nombre: String, numeroStr: String, nacionalidad: String, posicion: String, imagen: String) {
        val num = numeroStr.toIntOrNull() ?: 0
        val jugador = Jugadores(nombre = nombre, numero = num, nacionalidad = nacionalidad, posicion = posicion, imagen = imagen)
        jugadoresCollection.add(jugador)
        clearFields()
    }

    fun deleteJugador(idJugador: String) {
        jugadoresCollection.document(idJugador).delete()
    }

    fun clearFields() {
        name = ""
        numero = ""
        nacionalidad = ""
        posicion = ""
        equipo = ""
        imagen = ""
    }
}
