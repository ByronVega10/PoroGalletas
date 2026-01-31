package com.example.porogalletas.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.porogalletas.ui.components.PlatilloCardCompact
import com.example.porogalletas.viewmodel.PlatilloViewModel
import com.example.porogalletas.viewmodel.UsuarioViewModel
import com.example.porogalletas.ui.screen.LogoutDialog  // Ajusta si ruta diferente

@Composable
fun HomeScreen(
    navController: NavHostController,
    usuarioViewModel: UsuarioViewModel,
    platilloViewModel: PlatilloViewModel,
    onDarkThemeChanged: (Boolean) -> Unit  // ← Callback para cambiar tema
) {
    var showLogoutDialog by remember { mutableStateOf(false) }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Barra superior custom con BOTONES DE TEMA ☀️🌙
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "PoroGalletas",
                    style = MaterialTheme.typography.titleLarge
                )

                // BOTONES MODO CLARO/OSCuro ✅
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    TextButton(
                        onClick = { onDarkThemeChanged(false) }
                    ) {
                        Text("☀️ Claro")
                    }
                    TextButton(
                        onClick = { onDarkThemeChanged(true) }
                    ) {
                        Text("🌙 Oscuro")
                    }
                }

                IconButton(onClick = { showLogoutDialog = true }) {
                    Icon(
                        imageVector = Icons.Default.Logout,
                        contentDescription = "Cerrar sesión"
                    )
                }
            }

            Divider()

            // Contenido - LISTA PLATILLOS (IGUAL QUE ANTES)
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                items(platilloViewModel.platillos) { platillo ->
                    PlatilloCardCompact(platillo)
                }
            }

            // Dialogo Logout (IGUAL QUE ANTES)
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
}
