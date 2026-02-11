package com.example.examen_android_jesusmarquezruiz.model

import kotlinx.serialization.Serializable

@Serializable
data class Jugadores {
    var id: String = "",
    val nombre: String = "",
    val numero: Int = 0,
    val nacionalidad: String = "",
    val posicion: String = ""
}