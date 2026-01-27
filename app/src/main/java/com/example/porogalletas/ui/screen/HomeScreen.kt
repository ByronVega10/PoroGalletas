package com.example.porogalletas.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.porogalletas.viewmodel.PlatilloViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import com.example.porogalletas.ui.components.PlatilloCardCompact


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
                    .padding(16.dp, vertical = 12.dp),
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