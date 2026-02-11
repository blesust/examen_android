package com.example.examen_android_jesusmarquezruiz.model

import com.google.firebase.firestore.Exclude
import kotlinx.serialization.Serializable

@Serializable
data class Jugadores(
    @get:Exclude var id: String = "",
    val nombre: String = "",
    val numero: Int = 0,
    val nacionalidad: String = "",
    val posicion: String = "",
    val imagen: String = ""
)