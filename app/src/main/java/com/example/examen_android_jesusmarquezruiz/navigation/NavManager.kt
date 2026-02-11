package com.example.examen_android_jesusmarquezruiz.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import kotlinx.serialization.json.Json

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
        val Saver: Saver<NavManager, *> = listSaver(
            save = { it.backStack.map { route -> Json.encodeToString(route) } },
            restore = { savedList ->
                NavManager(savedList.map { Json.decodeFromString<NavRoutes>(it) })
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