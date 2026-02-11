package com.example.examen_android_jesusmarquezruiz.plantilla

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavEntry
import com.example.examen_android_jesusmarquezruiz.viewmodel.ProductViewModel


@Composable
fun PlantillaApp(){
    val authViewModel : AuthViewModel = viewModel()
    val jugadoresViewModel : ProductViewModel = viewModel()
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
            is NavRoutes.Login -> NavEntry(route){
                LoginScreen(
                    viewModel = authViewModel,
                    onNavigateToRegistro = { navManager.navigateTo(NavRoutes.Registro) },
                    onLoginSuccess = { navManager.navigateHome() }
                )
            }
            is NavRoutes.Home -> NavEntry(route) {
                HomeScreen(
                    authViewModel = authViewModel,
                    productViewModel = jugadoresViewModel,
                    onViewProduct = { navManager.navigateTo(NavRoutes.Producto(it)) },
                    onNavigateToConfirm = { tipo, id ->
                        navManager.navigateTo(NavRoutes.Confirmacion(tipo, id))
                    },
                    onLogout = { navManager.navigateTo(NavRoutes.Login) }
                )
            }
            is NavRoutes.Jugadores -> NavEntry(route) {
                JugadoresScreen(
                    product = route.jugadores,
                    onBack = { navManager.popBackStack() }
                )
            }

        }
    }
}


