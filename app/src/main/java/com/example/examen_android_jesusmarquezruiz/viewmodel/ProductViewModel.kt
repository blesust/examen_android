package com.example.examen_android_jesusmarquezruiz.viewmodel

import androidx.compose.runtime.mutableStateOf

class ProductViewModel : ViewModel(){
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

    private fun getJugadores(){
        jugadoresCollection.addSnapshotListener{ snapshot, e->
            if (e != null){
                Log.w("ProductViewModel", "Listen failed", e)
                return@addSnapshotListener
            }
            if (snapshot != null){
                val jugadores.value = snapshot.toObjects(Jugadores::class.java)
                jug?.id = doc.id
                jug
            }
        }
    }
}

fun addJugador(nombre: String, numero: Int, nacionalidad: String, posicion: String, imagen: String) {
    val jugadores = Jugadores(nombre = nombre, numero = numero, nacionalidad = nacionalidad, posicion = posicion, imagen = imagen)
    jugadoresCollection.add(jugadores)
    clearFields()
}

fun deleteJugador(idJugador: String) {
    jugadoresCollection.document(idJugador).delete()
}

fun startEditing(jugadores: Jugadores) {
    editingJugadores = jugadores
    name = jugadores.nombre
    numero = jugadores.numero
    nacionalidad = jugadores.nacionalidad
    posicion = jugadores.posicion
    equipo = jugadores.equipo
    imagen = jugadores.imagen
}

fun clearFields(){
    editingJugadores = null
    name = ""
    numero = ""
    nacionalidad = ""
    posicion = ""
    equipo = ""
    imagen = ""
}
