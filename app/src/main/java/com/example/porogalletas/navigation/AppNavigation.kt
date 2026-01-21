package com.example.porogalletas.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.porogalletas.ui.screen.MainScaffold
import com.example.porogalletas.ui.screen.LoginScreen
import com.example.porogalletas.viewmodel.UsuarioViewModel

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    val usuarioViewModel: UsuarioViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "Login"
    ) {
        composable("Login") {
            LoginScreen(navController, usuarioViewModel)
        }
        composable("Home") {
            MainScaffold(navController, usuarioViewModel)
        }
    }
}