package com.example.porogalletas.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.porogalletas.data.database.AppDatabase
import com.example.porogalletas.data.datastore.SessionManager
import com.example.porogalletas.data.usuario.UsuarioRepository
import com.example.porogalletas.ui.screen.MainScaffold
import com.example.porogalletas.ui.screen.RegisterScreen
import com.example.porogalletas.viewmodel.UsuarioViewModel
import com.example.porogalletas.viewmodel.UsuarioViewModelFactory
import androidx.compose.runtime.getValue
import com.example.porogalletas.viewmodel.PlatilloViewModel

@Composable
fun AppNavigation(onDarkThemeChanged: (Boolean) -> Unit) {  // ← CAMBIO: recibe callback
    val navController = rememberNavController()
    val context = LocalContext.current
    val database = remember { AppDatabase.getDatabase(context) }
    val usuarioRepository = remember { UsuarioRepository(database.usuarioDao()) }
    val sessionManager = remember { SessionManager(context) }

    val usuarioViewModel: UsuarioViewModel = viewModel(
        factory = UsuarioViewModelFactory(usuarioRepository, sessionManager)
    )
    val platilloViewModel: PlatilloViewModel = viewModel()

    val isLoggedIn by sessionManager.isLoggedIn.collectAsState(initial = false)

    LaunchedEffect(isLoggedIn) {
        if (isLoggedIn) {
            navController.navigate("Home") {
                popUpTo("Login") { inclusive = true }
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = "Login"
    ) {
        composable("Login") {
            RegisterScreen(navController, usuarioViewModel)
        }

        composable("Home") {
            MainScaffold(
                navController,
                usuarioViewModel = usuarioViewModel,
                platilloViewModel = platilloViewModel,
                onDarkThemeChanged = onDarkThemeChanged  // ← CAMBIO: pasa callback
            )
        }
    }
}
