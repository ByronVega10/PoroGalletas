package com.example.porogalletas.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.porogalletas.viewmodel.UsuarioViewModel
import com.example.porogalletas.ui.components.PlatilloCard
import com.example.porogalletas.viewmodel.PlatilloViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment



@Composable
fun HomeScreen(
    navController: NavHostController,
    usuarioViewModel: UsuarioViewModel,
    platilloViewModel: PlatilloViewModel

) {
    var showLogoutDialog by remember { mutableStateOf(false) }

    Scaffold { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            // Barra superior custom (NO experimental)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "PoroGalletas",
                    style = MaterialTheme.typography.titleLarge
                )

                IconButton(onClick = { showLogoutDialog = true }) {
                    Icon(
                        imageVector = Icons.Default.Logout,
                        contentDescription = "Cerrar sesión"
                    )
                }
            }

            Divider()

            // Contenido
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Bienvenido a PoroGalletas",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                platilloViewModel.platillos.forEach { platillo ->
                    PlatilloCard(platillo)
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }

        if (showLogoutDialog) {
            LogoutDialog(
                onConfirm = {
                    showLogoutDialog = false
                    usuarioViewModel.logout {
                        navController.navigate("Login") {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                },
                onDismiss = { showLogoutDialog = false }
            )
        }
    }
}