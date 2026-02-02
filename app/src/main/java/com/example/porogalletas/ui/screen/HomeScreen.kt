package com.example.porogalletas.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.porogalletas.ui.components.PlatilloCardCompact
import com.example.porogalletas.viewmodel.PlatilloViewModel
import com.example.porogalletas.viewmodel.UsuarioViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Arrangement

@Composable
fun HomeScreen(
    navController: NavHostController,
    usuarioViewModel: UsuarioViewModel,
    platilloViewModel: PlatilloViewModel,
    isDarkTheme: Boolean,
    onThemeToggle: () -> Unit
) {
    var showLogoutDialog by remember { mutableStateOf(false) }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Barra superior: Título | Switch ARRIBA | Logout ABAJO
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "PoroGalletas",
                        style = MaterialTheme.typography.titleLarge
                    )

                    // ← SWITCH ARRIBA DEL LOGOUT
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = if (isDarkTheme) Icons.Default.DarkMode else Icons.Default.LightMode,
                            contentDescription = "Tema",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(18.dp)
                        )
                        Switch(
                            checked = isDarkTheme,
                            onCheckedChange = { onThemeToggle() }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // ← LOGOUT ABAJO DEL SWITCH
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(onClick = { showLogoutDialog = true }) {
                        Icon(
                            imageVector = Icons.Default.Logout,
                            contentDescription = "Cerrar sesión"
                        )
                    }
                }
            }

            Divider()

            // Lista principal
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
