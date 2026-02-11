package com.example.examen_android_jesusmarquezruiz.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavRoutes {
    @Serializable
    data object Login : NavRoutes()

    @Serializable
    data object Home : NavRoutes()

    @Serializable
    data class Jugadores(val jugadores: Jugadores) : NavRoutes()
}