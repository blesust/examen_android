package com.example.examen_android_jesusmarquezruiz.navigation

import com.example.examen_android_jesusmarquezruiz.model.Jugadores
import kotlinx.serialization.Serializable

@Serializable
sealed class NavRoutes {
    @Serializable
    data object Login : NavRoutes()

    @Serializable
    data object Home : NavRoutes()

    @Serializable
    data class DetalleJugador(val jugador: Jugadores) : NavRoutes()
}
