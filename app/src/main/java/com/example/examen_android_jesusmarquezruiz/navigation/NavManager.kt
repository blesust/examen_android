package com.example.examen_android_jesusmarquezruiz.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule

class NavManager(initialBackStack: List<NavRoutes> = listOf(NavRoutes.Login)) {
    var backStack by mutableStateOf(initialBackStack)
        private set

    fun navigateTo(route: NavRoutes) {
        backStack = backStack + route
    }

    fun popBackStack() {
        if (backStack.size > 1) {
            backStack = backStack.dropLast(1)
        }
    }

    fun navigateHome() {
        backStack = listOf(NavRoutes.Home)
    }

    fun canGoBack(): Boolean = backStack.size > 1

    companion object {
        private val json = Json {
            serializersModule = SerializersModule { }
        }

        val Saver: Saver<NavManager, *> = listSaver(
            save = { navManager ->
                navManager.backStack.map { route -> json.encodeToString(NavRoutes.serializer(), route) }
            },
            restore = { savedList ->
                NavManager(savedList.map { jsonString -> json.decodeFromString(NavRoutes.serializer(), jsonString) })
            }
        )
    }
}

@Composable
fun rememberNavManager(initialRoute: NavRoutes = NavRoutes.Login): NavManager {
    return rememberSaveable(saver = NavManager.Saver) {
        NavManager(listOf(initialRoute))
    }
}
