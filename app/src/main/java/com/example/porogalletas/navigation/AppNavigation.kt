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
import com.example.porogalletas.data.datastore.SessionManager
import com.example.porogalletas.data.usuario.UsuarioRepository  // Asumiendo el path correcto
import com.example.porogalletas.ui.screen.MainScaffold
import com.example.porogalletas.ui.screen.RegisterScreen  // Asumiendo Login es RegisterScreen
import com.example.porogalletas.viewmodel.UsuarioViewModel
import com.example.porogalletas.viewmodel.UsuarioViewModelFactory
import com.example.porogalletas.viewmodel.PlatilloViewModel
import androidx.compose.runtime.getValue

@Composable
fun AppNavigation(
    isDarkTheme: Boolean,
    onThemeToggle: () -> Unit
) {
    val navController = rememberNavController()
    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }
    val usuarioApi = remember { UsuarioApiProvider.api }  // Corrige si el path es diferente
    val usuarioRepository = remember { UsuarioRepository(usuarioApi) }
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
                parentNavController = navController,
                usuarioViewModel = usuarioViewModel,
                platilloViewModel = platilloViewModel,
                isDarkTheme = isDarkTheme,
                onThemeToggle = onThemeToggle  // Propaga aquí
            )
        }
    }
}
