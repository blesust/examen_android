package com.example.examen_android_jesusmarquezruiz.plantilla

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.example.examen_android_jesusmarquezruiz.navigation.NavRoutes
import com.example.examen_android_jesusmarquezruiz.navigation.rememberNavManager
import com.example.examen_android_jesusmarquezruiz.screens.HomeScreen
import com.example.examen_android_jesusmarquezruiz.screens.JugadoresScreen
import com.example.examen_android_jesusmarquezruiz.screens.LoginScreen
import com.example.examen_android_jesusmarquezruiz.viewmodel.AuthViewModel
import com.example.examen_android_jesusmarquezruiz.viewmodel.ProductViewModel

@Composable
fun PlantillaApp() {
    val authViewModel: AuthViewModel = viewModel()
    val jugadorViewModel: ProductViewModel = viewModel()
    val navManager = rememberNavManager(initialRoute = NavRoutes.Login)

    BackHandler(enabled = navManager.canGoBack()) {
        navManager.popBackStack()
    }

    NavDisplay(
        backStack = navManager.backStack,
        modifier = Modifier.fillMaxSize(),
        onBack = { navManager.popBackStack() }
    ) { route: NavRoutes ->
        when (route) {
            is NavRoutes.Login -> NavEntry(route) {
                LoginScreen(
                    viewModel = authViewModel,
                    onLoginSuccess = { navManager.navigateHome() }
                )
            }
            is NavRoutes.Home -> NavEntry(route) {
                HomeScreen(
                    authViewModel = authViewModel,
                    jugadoresViewModel = jugadorViewModel,
                    onViewProduct = { navManager.navigateTo(NavRoutes.DetalleJugador(it)) },
                    onLogout = { navManager.navigateTo(NavRoutes.Login) }
                )
            }
            is NavRoutes.DetalleJugador -> NavEntry(route) {
                JugadoresScreen(
                    jugadores = route.jugador,
                    onBack = { navManager.popBackStack() }
                )
            }
        }
    }
}
